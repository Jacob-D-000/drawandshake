# Save State Research
By Jacob Dimoff

## Goal
The goal of the save state is to save the `View` class that is editted by the user during the apps runtime. This would inlcude the `canvasm`, `oaunt` objects mantaing there attribue values.

As with my previous research, I went to the android Devevleopers website to find out what I coulld in regards to creating save states. https://developer.android.com/topic/libraries/architecture/saving-states. 

The first step was to determine how a `view` is saved. 

It seems that the bet way to to create a `Bundle`