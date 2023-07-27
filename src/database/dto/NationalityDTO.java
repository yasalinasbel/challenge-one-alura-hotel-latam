package database.dto;

public enum NationalityDTO {

	AFGAN ("AFGAN","afgano-afgana"),
	GERMAN ("GERMAN","alemán-alemana"),
	ARGENTIN ("ARGENTIN","argentino-argentina");
	
	private final String name;
	private final String spanishNationality;
	
	private NationalityDTO(String name, String spanishNationality) {
		this.name=name;
		this.spanishNationality=spanishNationality;
	}

	public String getName(){
		return name;
	}
}
