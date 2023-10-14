# Save State Research
By Jacob Dimoff

## Goal
The goal of the save state is to save the `View` class that is edited by the user during the app's runtime. This would include the `canvas`, and `paint` objects maintaining their attribute values.

As with my previous research, I went to the Android Developers website to find out what I could in regards to creating save states. https://developer.android.com/topic/libraries/architecture/saving-states. 

The first step was to determine how a `view` is saved. 

It seems that the best way to create a `Bundle` which is a class that is used to pass data between Kotlin activities by storing them as `Parcelable` which is an interface for a class.

Links explaining buddles:
- https://www.geeksforgeeks.org/bundle-in-android-with-example/
- https://developer.android.com/reference/android/os/Bundle

This means that before we even save the state, we need to parcel all the classes we want to save (see above) and then put them into a bundle that will saved until the app is loaded again. Then we pull these parcels out and use them as the default when the draw screen is shown

First, when creating classes whose objects we would save, we need to `Parcelable`. These classes would also need a companion object that will implement `Parcelable.Creator` and then include all the other methods and attributes that class would have https://developer.android.com/reference/android/os/Parcelable.

Next, we would have to store these parcelable objects into a bundle. This could be done in a couple of ways
- Storing the Parcelable into an ArrayList and inputting it into the bundle using    `getParcelableArray(String key, Class<T> clazz) `
- Adding the Parcelable individually using `getParcelableArray(String key, Class<T> clazz) `

either way, this will make sure all the data from these classes will be stored in the bundle.

Next, we will have to save the bundle otherwise we have just made an overcomplicated Array of separate object types. We can use the `protected void onSaveInstanceState (Bundle outState)` which is a method of the `Activity` class. This function would be called right before the activity is killed and could be reinstated when it's restored using `onCreate(Bundle)` or `onRestoreInstanceState(Bundle)`

We might not even need to make parcels since if the canvas and paint are both part of the view, all we need to do is call `View.onSaveInstanceState()` which will save everything in the hierarchy. I would recommend we attempt this way first and if that doesn't work, use the previous method talked about above to achieve this same goal.

So after further research, the above-mentioned work won't work when it comes to closing the app or killing its processes. It only saves between activities so long as the app is running. 

We can't use SharedPreferences since it only works on primitive types, and `PersistableBundle` is an option but since the definition of a "simple object" is quite vague, I have no idea if it will work or not, the only other possibility is to make a private database we pull the class information form which is means we are going to need a database

Overall. I think this leads us to two options
- Create a database to store the information
- Create a text file that stores the information we read from when we first launch the app
- Scrap the entire idea of persistence after the app has been closed.

I think the best for us is to create a file containing the data like a config file. This would be lightweight and relatively easy to pull from. The only issue would be that we need to make sure that the user can't mess with the file and if they can, we need to do error checks to make sure that all the data needed to reinstate the object is there If not, we can just start from scratch.