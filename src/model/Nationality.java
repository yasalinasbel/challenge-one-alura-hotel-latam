package model;

public enum Nationality {

	AFGANISTAN ("AFGAN","afgano-afgana"),
	ALEMANIA ("GERMAN","alem\u00e1n-alemana"),
	ARGENTINA ("ARGENTIN","argentino-argentina"),
	ARABIA ("ARABIA","\u00e1rabe-\u00e1rabe"),
	AUSTRALIA ("AUSTRALIA","australiano-australiana"),
	BELGICA("BELGICA","belga-belga"),
	BOLIVIA ("BOLIVIA","boliviano-boliviana"),
	BRASIL ("BRASIL","brasile\u00f1o-brasile\u00f1a"),
	CAMBOYA ("CAMBOYA","camboyano-camboyana"),
	CANADA ("CANADÁ","canadiense-canadiense"),
	CHILE("CHILE","chileno-chilena"),
	CHINA ("CHINA","chino-china"),
	COLOMBIA ("COLOMBIA","colombiano-colombiana"),
	COREA ("COREA","coreano-coreana"),
	COSTARICA ("COSTARICA","costarricense-costarricense"),
	CUBA ("CUBA","cubano-cubana"),
	DINAMARCA ("DINAMARCA","dan\u00e9s-danesa"),
	REPUGLICADOMINICANA ("REPUBLICADOMINICANA","dominicano-dominicana"),
	ECUADOR ("ECUADOR","ecuatoriano-ecuatoriana"),
	EGIPTO ("EGIPTO","egipcio-egipcia"),
	SALVADOR ("SALVADOR","salvadore\u00f1o-salvadore\u00f1a"),
	ESCOCIA ("ESCOCIA","escoc\u00e9s-escocesa"),
	ESPAÑA ("ESPAÑA","espa\u00f1ol-espa\u00f1ola"),
	ESTADOSUNIDOS ("ESTADOSUNIDOS","estadounidense-estadounidense"),
	ESTONIA ("ESTONIA","estonio-estonia"),
	ETIOPIA ("ETIOPIA","etiope-etiope"),
	FILIPINAS ("FILIPINAS","filipino-filipina"),
	FINLANDIA ("FINLANDIA","finland\u00e9s-finlandesa"),
	FRANCIA ("FRANCIA","franc\u00e9s-francesa"),
	REINOUNIDO ("REINOUNIDO","gal\u00e9s-galesa"),
	GRECIA ("GRECIA","griego-griega"),
	GUATEMALA ("GUATEMALA","guatemalteco-guatemalteca"),
	HAITI ("HAITI","haitiano-haitiana"),
	HOLANDA ("HOLANDA","holand\u00e9s-holandesa"),
	HONDURAS ("HONDURAS","hondure\u00f1o-hondure\u00f1a"),
	INDONESIA ("INDONESIA","indon\u00e9s-indonesa"),
	INGLATERRA ("INGLATERRA","ingl\u00e9s-inglesa"),
	IRAQ ("IRAQ","iraqu\u00ed-iraqu\u00ed"),
	IRAN ("IRAN","iran\u00ed-iran\u00ed"),
	IRLANDA ("IRLANDA","irland\u00e9s-irlandesa"),
	ISRAEL ("ISRAEL","israel\u00e9ed-israel\u00e9ed"),
	ITALIA ("ITALIA","italiano-italiana"),
	JAPON ("JAPON","japon\u00e9s-japonesa"),
	JORDANIA ("JORDANIA","jordano-jordana"),
	LAOS ("LAOS","laosiano-laosiana"),
	LETONIA ("LETONIA","let\u00f3n-letona"),
	MALASIA ("MALASIA","malayo-malaya"),
	MARRUECOS ("MARRUECOS","marroqu\u00ed-marroqu\u00ed"),
	MEXICO ("MEXICO","mexicano-mexicana"),
	NICARAGUA ("NICARAGUA","nicarag\u00fcense-nicarag\u00fcense"),
	NORUEGA ("NORUEGA","noruego-noruega"),
	NUEVAZELANDA ("NUEVAZELANDA","neozeland\u00e9s-neozelandesa"),
	PANAMA ("PANAMA","paname\u00f1o-paname\u00f1a"),
	PARAGUAY ("PARAGUAY","paraguayo-paraguaya"),
	PERU ("PERU","peruano-peruana"),
	POLONIA ("POLONIA","polaco-polaca"),
	PORTUGAL ("PORTUGAL","portugu\u00e9s-portuguesa"),
	PUERTORICO ("PUERTORICO","puertorrique\u00f1o-puertorrique\u00f1o"),
	RUMANIA ("RUMANIA","rumano-rumana"),
	RUSIA ("RUSIA","ruso-rusa"),
	SUECIA ("SUECIA","sueco-sueca"),
	SUIZA ("SUIZA","suizo-suiza"),
	TAILANDIA ("TAILANDIA","tailand\u00e9s-tailandesa"),
	TAIWAN ("TAIWAN","taiwan\u00e9s-taiwanesa"),
	TURQUIA ("TURQUIA","turco-turca"),
	UCRANIA ("UCRANIA","ucraniano-ucraniana"),
	URUGUAY ("URUGUAY","uruguayo-uruguaya"),
	VENEZUELA ("VENEZUELA","venezolano-venezolana"),
	VIETNAM ("VIETNAM","vietnamita-vietnamita");

	
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
