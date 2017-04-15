package lab11;

public class CatalogItem <S extends Media> implements Comparable <CatalogItem<S>>{
	
	S media;
	
	public CatalogItem(S media){
		this.media = media;
	}

	@Override
	public int compareTo(CatalogItem<S> otherItem) {
		return media.getId().compareTo(otherItem.media.getId());
	}

	public String toString(){
		return media.getId() + " " + media.getCreator() + " " + media.getTitle() + " " + media.getYear()+"\n";
	}
}
