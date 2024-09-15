public class Test {
    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/test")
    public void test() throws Exception {
        String url = "http://localhost:8081/postCompressionTest";  // Replace with the actual URL

        HttpHeaders headers = new HttpHeaders();
        /*headers.set("Content-Encoding", "gzip");
        headers.set("Content-Type", "application/json");*/

        // Create the request body as a Map
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("abc", "xyz");

        // Convert the Map to a JSON string and compress it
       // String jsonRequestBody = convertMapToJson(requestBodyMap);
       // byte[] compressedRequestBody = compressRequestBody(requestBodyMap);

        // Create the HttpEntity containing the compressed request body and headers
        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(requestBodyMap, headers);

        // Send the request
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);

        // Check response headers and body
        String contentEncoding = response.getHeaders().getFirst(HttpHeaders.CONTENT_ENCODING);
        System.out.println("Content-Encoding in response: " + contentEncoding);

        // Optional: Process the response body if needed
    }

    // Method to compress data using GZIP
    public byte[] compressRequestBody(Map<String, Object> requestBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonBytes = objectMapper.writeValueAsBytes(requestBody);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(jsonBytes);
        }
        return byteArrayOutputStream.toByteArray();

        // Method to convert Map to JSON (standard practice for web requests)
    }
}
