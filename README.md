# retomercadolibre
Esta es la solución al reto de los mutantes para desarrollador backend de mercado libre

# retomercadolibre

para hacer uso del api se debe llevar a cabo los siguientes pasos:

1. Descargar e instalar postman
2. Para usar el endpoint /mutant/ tenemos lo siguiente:
	a. El endpoint tiene la siguiente url: http://18.217.239.190:8080/apirestmutantes/mutant/
	b. El verbo a usar es POST.
	c. Se debe enviar en el body la información con la siguiente estructura: {"dna":["CTGCGA","CAACGC","ATCTAC","CGAAAA","CACATA","TACGCG"]}
	   pueden haber 3 tipos de respuestas

	   c.1. si la cadena de DNA no supera las validaciones de estructura y datos devolvera un mensaje: HTTP 500-Internal Server Error y un mensaje en el body como el siguiente.
	   
	      - {"dna":"{\"dna\":[\"CTGCGA\",\"CAACGC\",\"ATCTAC\",\"CGAAAA\",\"CACATA\",\"TACGG\"]}","type":"E","exist":false,"message":"La información enviada como DNA presenta inconsistencias, las tramas no son del mismo tamaño"}
	      - {"dna":"{\"dna\":[\"CTGCGA\",\"CAACGC\",\"ATCTAC\",\"CGAAAA\",\"CTCATA\",\"TACGC1\"]}","type":"E","exist":false,"message":"La información enviada como DNA presenta inconsistencias, los datos tienen información erronea"}
	   
	      - Donde type:E indica que es un error.
	   
	   c.2. Si supera la validación de los datos y resulta ser un mutante: HTTP 200-OK y un mensaje en el body como el siguiente:
		  
		  - {"dna":"{\"dna\":[\"CTGCGA\",\"CAACGC\",\"ATCTAC\",\"CGAAAA\",\"CTCATA\",\"TACGCA\"]}","type":"M","exist":false,"message":"El DNA es mutante, es nuevo y se guardó satisfactoriamente en la BD"}
	   
		  - Donde type: "M" indica que es mutante y exist indica "false" que no existe en la bd y "true" que si existe.
		  
	   c.3. Si supera la validación de los datos y resulta ser un humano: HTTP 403-Forbidden y un mensaje en el body como el siguiente:
	   
		  - {"dna":"{\"dna\":[\"CTGCGA\",\"CAACGC\",\"ATCTAC\",\"CGACAA\",\"CTCATA\",\"TACGCA\"]}","type":"H","exist":false,"message":"El DNA es humano, es nuevo y se guardó satisfactoriamente en la BD"}
		  
		  - Donde type: "H" indica que es mutante y exist indica "false" que no existe en la bd y "true" que si existe.
3. Para usar el enpoint /stats tenemos lo siguiente
	a. El endpoint riene la siguiente url: http://18.217.239.190:8080/apirestmutantes/stats
	b. El verbo a usar es GET.
	c. El resultado sera una respuesta con la siguiente estructura: {"count_mutant_dna":3,"count_human_dna":1,"ratio":3.0}
