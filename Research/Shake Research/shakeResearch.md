# Shake Research
By Jacob Dimoff

The goal of these notes is to find relevant Kotlin libraries that contain relevant info on how to let the user shake their phone as input so we can remove items from the screen.
<!-- This source code will later be translated into a PDF for easier readability for users if needed. -->

Some things that I would like to find include:
- Event listener
- Determining shake intensity
- How to remove things from the screen after a shake.

Because of the success of my previous research, I decided to check the Android development website to see what I could find on shake detection. The first thing I found was an overview of sensors for Android https://developer.android.com/guide/topics/sensors/sensors_overview

There are three different sensors built into androids but the most important for our use case is the motion sensor. This sensor generally seems to be hardware-based which means that `android.hardware` https://developer.android.com/reference/android/hardware/package-summary package will need to be imported when making a class to collect sensor data. This package includes:
- sensorManager: manages the data and makes sure that a sensor exists when it needs to
- sensor: Class we would use to create the sensor for motion
- sensorEvent: Where the raw data from the motion is stored (type of motion, accuracy, timestamp, etc.)
- sensorEvertListener: create a callback that either change values (when the event happens) and their accuracy

Because We will be using the accelerometer sensor, we do not need to worry about the version of the phone because Android since 1.5 have motion sensors. If we do want to make sure the sensor is onboard, we could use `SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)`.

Another thing that will be important is to determine how frequently we would be checking for that shaking motion. To determine the minimum time needed before executing a check, we could use `getMinDelay` (displayed in milliseconds.)

To report a change of data in a sensor, the sensorEvent Method `onSensorChanged()` event will be called to provide data.

The next important link for our needs on Android developers is the Motion sensors page https://developer.android.com/guide/topics/sensors/sensors_motion

There appear to be two different accelerometers we could use TYPE_ACCELEROMETER and TYPE_ACCELEROMETER_UNCALIBRATED 

In this case, I think using the regular TYPE_ACCELEROMETER would work best because it has existed since API level 3 meaning that our app would work on pretty much any phone. It should be noted that if we use this type, we will also have to factor gravity into the equations we make for acceleration which means that we must subtract gravity from the total acceleration (9.81m/s^2)

acceleration is determined using an X - Y - Z
- Left (positive) to Right is X (negative)
- Away (positive )and forward horizontally (negative)
- up (positive) and down (negative)

It is important to note some other behaviors of x-y-z including the fact that the orientation of x-y-z is based on the default orientation (portrait and landscape) so our app would need to check the orientation of the phone and change the coords with `remapCoordinateSystem()` so they are landscape oriented

## Action plan
With all of this in mind, I think I can write up a basic action plan for the implementation of the shake.

1. When the app is first launched, we check to see if the phone contains the proper hardware to check motion (it probably does but it is worth checking in the event the sensors are blocked or non-functional) 
2. Create all appropriate objects (sensor, event handle, manager, and listener) if the sensor works properly.
3. The event handle will contain the functions required to change the screen during the shake, (if acceleration >= eraseShakeAcc { remove items from the screen } ). The most important determining factor will be how often we can check the acceleration. If the check is too infrequent, the change would be very rough and inconsistent. If we need to collect data faster than normal (normal as in normal for Android) we would have to override it and determine our frequency of collection.

How we ultimately change the screen is still up in the air, but I think that this data should be enough to at least collect the necessary information to determine when a shake occurs and to implement some kind of event do to that fact.