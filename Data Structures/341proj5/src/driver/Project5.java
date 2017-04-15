package driver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import proj5.*;

/**
* @version 5/13/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 5
* Section 02
*/

public class Project5 {

	public static void main(String args[]){
		int runs = 1;
		int runi = 8;
		int runj = 1;
		System.out.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
		System.out.println("Available memory (bytes): " + Runtime.getRuntime().freeMemory());
		//i will be 256, 512, 1024 ,2048
		for(int i = 256; i <= 2048; i = i*2){
			Buffer buffer = new Buffer(i);
			Satellite satellite = new Satellite(buffer);
			Thread satThread = new Thread(satellite);
			satThread.start();
			//j will be either 10, 100, 1000, 10000, 100000
			for(int j = 10; j <= 100000; j = j*10){
				System.out.println("\nRun #" + runs + ": i=" + runi + " j=" + runj + " N=" + i + " B1=" + (i*i)*2 + " B2=" + (i*i) + " T=" + j);
				//create the processor and make it a thread and start it
				Processor processor = new Processor(buffer);
				Thread proThread = new Thread(processor);
				proThread.start();
				//makes the processor thread wait for the buffer to have enough data for it to take out
				while(!processor.hasDataFromBuffer()){}
				processor.stopRunning();
				int[] processorData = processor.getDataFromProcessor();
				//begin the mergesort and time it
				long t1 = System.currentTimeMillis();
				processorData = MergeSort.merge(processorData, j);
				long t2 = System.currentTimeMillis();
				System.out.println("Time mergesort: " + (t2-t1) + "ms");
				//create a normalized array of values the integers will be betweem 0 and 255
				int[] BArray = normalize(processorData);
				LinkedList<Integer> linky = new LinkedList<Integer>();
				//convert the normalized array to a linked list
				for(int k : BArray){
					linky.add(k);
				}
				String filename = "images/output_N"+i+"_T" + j + ".jpg";
				System.out.println("Saving image: " + filename);
				//creates the image
				save_image(linky, i, filename);
				runs++;
				runj++;
			}
			satellite.stopRunning();
			runi++;
		}
		System.exit(0);
	}
	
	/**
	 * normalizes the value in the given array between 0 and 255
	 * @param array
	 * @return
	 */
	private static int[] normalize(int[] array){
		int[] BArray = new int[array.length];
		for(int i = 0; i < array.length; i++){
			BArray[i] = ((255)*(array[i]))/4096;
		}
		return BArray;
	}
	
	/* Save image from an Integer buffer (assues integers are between [0-255]) */
   private static int save_image(LinkedList<Integer> buffer, int N, String filename){
         int width = N;
         int height = N;

         /* Make sure buffer is the correct size */
         if (buffer.size() != N*N){
            System.out.println("ERROR: Arrays of different sizes\n");
            return -1;
         }
           
         /* Create buffered image and loop through buffer adding pixels */
	 try {
          BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

          /*Loop through buffer and assign pixel value*/
          int i=0;
          Integer value=0;
          Iterator<Integer> iter = buffer.iterator();
          while (iter.hasNext()) {
             value = iter.next();
             bufferedImage.setRGB((int)i%width, (int)i/width, (value << 16) + (value << 8) + value);
             i+=1;
          }

          //Save image
	  ImageIO.write(bufferedImage, "jpg", new File( filename ));
         }
         catch (IOException e) {
           System.out.println(e.getMessage());
         }

         return 1;
   }

}
