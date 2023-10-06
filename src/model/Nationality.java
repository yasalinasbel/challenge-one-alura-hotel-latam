package model;

public enum Nationality {

	AFGHANISTAN ("AFGAN", "afgano-afgana"),
	GERMANY ("GERMAN", "alem\u00e1n-alemana"),
	ARGENTINA ("ARGENTIN", "argentino-argentina"),
	ARABIA ("ARABIA", "\u00e1rabe-\u00e1rabe"),
	AUSTRALIA ("AUSTRALIA", "australiano-australiana"),
	BELGIUM ("BELGIUM", "belga-belga"),
	BOLIVIA ("BOLIVIA", "boliviano-boliviana"),
	BRAZIL ("BRAZIL", "brasile\u00f1o-brasile\u00f1a"),
	CAMBODIA ("CAMBODIA", "camboyano-camboyana"),
	CANADA ("CANADA", "canadiense-canadiense"),
	CHILE ("CHILE", "chileno-chilena"),
	CHINA ("CHINA", "chino-china"),
	COLOMBIA ("COLOMBIA", "colombiano-colombiana"),
	KOREA ("KOREA", "coreano-coreana"),
	COSTARICA ("COSTA RICA", "costarricense-costarricense"),
	CUBA ("CUBA", "cubano-cubana"),
	DENMARK ("DENMARK", "dan\u00e9s-danesa"),
	DOMINICANREPUBLIC ("DOMINICAN REPUBLIC", "dominicano-dominicana"),
	ECUADOR ("ECUADOR", "ecuatoriano-ecuatoriana"),
	EGYPT ("EGYPT", "egipcio-egipcia"),
	ELSALVADOR ("EL SALVADOR", "salvadore\u00f1o-salvadore\u00f1a"),
	SCOTLAND ("SCOTLAND", "escoc\u00e9s-escocesa"),
	SPAIN ("SPAIN", "espa\u00f1ol-espa\u00f1ola"),
	UNITEDSTATES ("UNITED STATES", "estadounidense-estadounidense"),
	ESTONIA ("ESTONIA", "estonio-estonia"),
	ETHIOPIA ("ETHIOPIA", "et\u00edope-et\u00edope"),
	PHILIPPINES ("PHILIPPINES", "filipino-filipina"),
	FINLAND ("FINLAND", "finland\u00e9s-finlandesa"),
	FRANCE ("FRANCE", "franc\u00e9s-francesa"),
	UNITEDKINGDOM ("UNITED KINGDOM", "brit\u00e1nico-brit\u00e1nica"),
	GREECE ("GREECE", "griego-griega"),
	GUATEMALA ("GUATEMALA", "guatemalteco-guatemalteca"),
	HAITI ("HAITI", "haitiano-haitiana"),
	NETHERLANDS ("NETHERLANDS", "neerland\u00e9s-neerlandesa"),
	HONDURAS ("HONDURAS", "hondure\u00f1o-hondure\u00f1a"),
	INDONESIA ("INDONESIA", "indonesio-indonesia"),
	ENGLAND ("ENGLAND", "ingl\u00e9s-inglesa"),
	IRAQ ("IRAQ", "iraqu\u00ed-iraqu\u00ed"),
	IRAN ("IRAN", "iran\u00ed-iran\u00ed"),
	IRELAND ("IRELAND", "irland\u00e9s-irlandesa"),
	ISRAEL ("ISRAEL", "israel\u00ed-israel\u00ed"),
	ITALY ("ITALY", "italiano-italiana"),
	JAPAN ("JAPAN", "japon\u00e9s-japonesa"),
	JORDAN ("JORDAN", "jordano-jordana"),
	LAOS ("LAOS", "laosiano-laosiana"),
	LATVIA ("LATVIA", "let\u00e9n-letona"),
	MALAYSIA ("MALAYSIA", "malayo-malaya"),
	MOROCCO ("MOROCCO", "marroqu\u00ed-marroqu\u00ed"),
	MEXICO ("MEXICO", "mexicano-mexicana"),
	NICARAGUA ("NICARAGUA", "nicarag\u00fcense-nicarag\u00fcense"),
	NORWAY ("NORWAY", "noruego-noruega"),
	NEWZEALAND ("NEW ZEALAND", "neozeland\u00e9s-neozelandesa"),
	PANAMA ("PANAMA", "paname\u00f1o-paname\u00f1a"),
	PARAGUAY ("PARAGUAY", "paraguayo-paraguaya"),
	PERU ("PERU", "peruano-peruana"),
	POLAND ("POLAND", "polaco-polaca"),
	PORTUGAL ("PORTUGAL", "portugu\u00e9s-portuguesa"),
	PUERTORICO ("PUERTO RICO", "puertorrique\u00f1o-puertorrique\u00f1a"),
	ROMANIA ("ROMANIA", "rumano-rumana"),
	RUSSIA ("RUSSIA", "ruso-rusa"),
	SWEDEN ("SWEDEN", "sueco-sueca"),
	SWITZERLAND ("SWITZERLAND", "suizo-suiza"),
	THAILAND ("THAILAND", "tailand\u00e9s-tailandesa"),
	TAIWAN ("TAIWAN", "taiwan\u00e9s-taiwanesa"),
	TURKEY ("TURKEY", "turco-turca"),
	UKRAINE ("UKRAINE", "ucraniano-ucraniana"),
	URUGUAY ("URUGUAY", "uruguayo-uruguaya"),
	VENEZUELA ("VENEZUELA", "venezolano-venezolana"),
	VIETNAM ("VIETNAM", "vietnamita-vietnamita");

	
	private final String name;
	private final String spanishNationality;
	
	private Nationality(String name, String spanishNationality) {
		this.name=name;
		this.spanishNationality=spanishNationality;
	}

	public String getName(){
		return name;
	}
	
	@Override
	public String toString() {
		return spanishNationality;
	}
}
