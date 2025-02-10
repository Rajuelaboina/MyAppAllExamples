package com.phycaresolutions.mymap.pushnotifirebase;


public class FirebaseHelper {
    /*private static final String FIREBASE_CONFIG_PATH = "/path/to/your/firebase-service-account-file.json"; // Update with the correct path
    private static final String FCM_SERVER_KEY = "fAqAnWA06L6UpHwgrS4s_IMNuVpptaGPF-iydfUP9cs";  // Get this from Firebase Console
   static Context mContext;
    // Initialize Firebase SDK
    public static void initializeFirebase(Context context) {
        mContext = context;
        try {
            FileInputStream serviceAccount = new FileInputStream(FIREBASE_CONFIG_PATH);
            FirebaseOptions options = new FirebaseOptions.Builder()
                   // .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(mContext);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error initializing Firebase: " + e.getMessage());
        }
    }

    // Send push notification to a specific device using FCM token
    public static String sendPushNotificationToDevice(String deviceToken, String title, String body) {
        try {
            Message message = Message.builder()
                    .setToken(deviceToken)  // The device token of the recipient device
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending notification: " + e.getMessage());
            return null;
        }
    }

    // Send push notification to a topic (e.g., 'sports')
    public static String sendPushNotificationToTopic(String topic, String title, String body) {
        try {
            Message message = Message.builder()
                    .setTopic(topic)  // The topic name (e.g., "sports")
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .build();


            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message to topic: " + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending notification to topic: " + e.getMessage());
            return null;
        }
    }*/
}