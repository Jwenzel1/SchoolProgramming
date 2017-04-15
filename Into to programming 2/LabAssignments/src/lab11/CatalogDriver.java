package lab11;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class CatalogDriver 
{

	/**
	 * This method uses Insertion-Sort to sort the specified List of CatalogItems.
	 * @param <T> the ID type for each CatalogItem in catalogs over which CatalogItems are
	 * ultimately sorted
	 * @param <S> the Media type which each CatalogItem in catalogs contains
	 * @param catalog the List of CatalogItems to sort
	 */
	public static<S extends Media> void sort( List<CatalogItem<S>> catalog )
	{
		int j;
		for( int i = 1; i < catalog.size(); i++ )
		{
			j = i;
			while( j > 0 && catalog.get( j ).compareTo( catalog.get( j - 1 ) ) < 0 )
			{
				Collections.swap( catalog, j, j - 1 );	
				j--;
			}
		}
	}
	
	public static void main( String[] args ) 
	{
		//Create an ArrayList of CatalogItems named list1
		//This ArrayList must take a type parameter of CatalogItem
		//CatalogItem in turn should take a type parameter CD
		//---here----
		
		ArrayList<CatalogItem<CD>> list1 = new ArrayList<CatalogItem<CD>>();
		ArrayList<CatalogItem<Book>> list2 = new ArrayList<CatalogItem<Book>>();
		
		//adding CatalogItems	
		list1.add( new CatalogItem<CD>(new CD( 0,"Lady Gaga", "The Fame", 2008 ) ) );
		list1.add( new CatalogItem<CD>(new CD(7, "Lady Gaga", "The Fame Monster", 2009 ) ) );
		list1.add( new CatalogItem<CD>(new CD(5, "Jay-Z", "The Blueprint 3", 2009 ) ) );
		list1.add( new CatalogItem<CD>(new CD(2, "Santana", "Supernatural", 1999 ) ) );
		
		//printing the iPod catalog before and after sorting
		System.out.println( "CD Catalog:" );
		System.out.println( "Before Sorting:" );
		System.out.println( list1 );
		sort( list1 );
		System.out.println( "\nAfter Sorting by ID:" );
		System.out.println( list1 );

		//Create an ArrayList of CatalogItems named list2
		//This ArrayList must take a type parameter of CatalogItem
		//CatalogItem in turn should take a type parameter Book
		//---here-----
		
		//adding CatalogItems
		list2.add( new CatalogItem<Book>(  new Book( "B5"," Atlas Shrugged ", " Ayn Rand ", 1957 ) ) );
		list2.add( new CatalogItem<Book>(  new Book( "A0"," Lord of the Rings: Fellowship of the Ring ", " J.R.R. Tolkien ", 1954 ) ) );
		list2.add( new CatalogItem<Book>(  new Book(  "C2"," Even Cowgirls Get the Blues ", " Tom Robbins ", 1976 ) ) );
		list2.add( new CatalogItem<Book>(  new Book( "A1"," The Subtle Knife ", " Philip Pullman ", 1997 ) ) );		
		
		//printing the kindle catalog before and after sorting
		System.out.println( "\n\nBook Catalog:" );
		System.out.println( "Before Sorting:" );
		System.out.println( list2 );
		sort( list2 );
		System.out.println( "\nAfter Sorting by ID:" );
		System.out.println( list2 );
	}
}