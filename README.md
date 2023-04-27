# Final-AustenL-DennisB-BeethovenM-JulianR

# Get Together
A social messaging app designed for easy and streamlined communication among students and peers. Get together and stay together through an in-app messaging system and profile customization!
This app was created using Firebase as our back end, we have basic security rules in place that only allow authorized users to access our read and write permissions.

# Authors
Austen Lowder\
Dennis Beaver\
Beethoven Meginnis\
Julian Rodriguez

# Build Instructions
*Runs on the latest edition of Android Studio\
*Tutorial we used to help us through the project https://www.youtube.com/playlist?list=PL0dzCUj1L5JE-jiBHjxlmXEkQkum_M3R- \
*Use gradle 7.5 for proper run experience\
*Verify that the compile SDK version is 33 (Android 13.0 - Tiramisu)\
*Before running app insure that you have invalidated caches and restarted the IDE ex. Images


![image](https://user-images.githubusercontent.com/112116586/232137208-aef1079a-ca62-46d7-aa34-347d3dffca21.png)
![image (1)](https://user-images.githubusercontent.com/112116586/232137229-d400d01f-adee-4740-9e31-21365d17bfee.png)

*In order to run app, add an android app under the configuration settings, and ensure that it runs under the main module, then the app should be ready to run!\
*When closing application, make sure to sign out to avoid potential errors with account data storage.\
*When creating an account, please use a Google email to ensure proper email address validation.\
*For any questions or bugs, please reach out to gettogetherdomain@gmail.com!\

# Suppressed Warning and Justification
*SetTextI18n: This warning relates to the apps use of "$" when pulling data. Since some of our data is hardcoded, it made more sense to suppress this warning.\
*DEPRECATION: This warning relates to our use of deprecated features in the process of accepting user input. While we did find other methods for accepting these values, the deprecated features were most efficient for what we were trying to accomplish.