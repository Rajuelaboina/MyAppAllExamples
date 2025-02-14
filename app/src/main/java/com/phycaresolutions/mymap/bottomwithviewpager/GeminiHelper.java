package com.phycaresolutions.mymap.bottomwithviewpager;


public class GeminiHelper {
   /* private static final String TAG = "GeminiHelper";
    private final String apiKey;
    private final String modelName = "gemini-pro";
    private final GenerativeModel model;
    private ExecutorService executorService;

    public interface GeminiResponseListener {
        void onResponse(String generatedText);

        void onError(String errorMessage);
    }

    public GeminiHelper(String apiKey) {
        this.apiKey = apiKey;
        this.model = new GenerativeModel(apiKey, modelName);
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void generateContent(String promptText, GeminiResponseListener listener) {
        executorService.execute(() -> {
            try {
                Part part = Part.newBuilder().setText(promptText).build();
                Content content = Content.newBuilder().addParts(part).setRole("user").build();
                Prompt prompt = Prompt.newBuilder().addContents(content).build();
                List<SafetySetting> safetySettings = Arrays.asList(
                        SafetySetting.newBuilder()
                                .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                                .setThreshold(HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                                .build(),
                        SafetySetting.newBuilder()
                                .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                                .setThreshold(HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                                .build(),
                        SafetySetting.newBuilder()
                                .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                                .setThreshold(HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                                .build(),
                        SafetySetting.newBuilder()
                                .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                                .setThreshold(HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                                .build()
                );
                GenerateContentRequest request = GenerateContentRequest.newBuilder().setPrompt(prompt).addAllSafetySettings(safetySettings).build();
                Future<GenerateContentResponse> responseFuture = model.generateContent(request);
                GenerateContentResponse response = responseFuture.get();
                List<Content> contents = response.getContentsList();
                for (Content contentResponse : contents) {
                    List<Part> parts = contentResponse.getPartsList();
                    for (Part partResponse : parts) {
                        String generatedText = partResponse.getText();
                        listener.onResponse(generatedText);
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                Log.e(TAG, "Error generating content", e);
                listener.onError("Error generating content:" + e.getMessage());
            }
        });
    }
    public void shutdown() {
        executorService.shutdown();
    }*/
}


