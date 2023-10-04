# Draw Research
By Jacob Dimoff

The goal of these notes is to find relevant kotlin libraries that contain relevant info on how to let a suer draw contents to the screen.
<!-- This source code will later be translated into a pdf for easier readability for users if needed. -->

The first set of this is to search whether Kotlin has anything to help draw to the screen, my search was "draw to the screen kotlin".

The first result was to the android developers website with title "Custom Drawings" https://developer.android.com/develop/ui/views/layout/custom-views/custom-drawing

This source seem pretty useful because it not only explains how to implement the ability to draw to the screen, but also some of the theory behind it. 

The first step to making a drawable screen is to over ride the onDraw()method which is a method of the view class. This will allow you you to access the Canvas class

The Canvas class it the medium on which one can draw.

In order to determine how we draw, a paint class(es) need to be made to dictates color and pixel size

The next important section is to make the custom canvas view interactive https://developer.android.com/develop/ui/views/layout/custom-views/making-interactive

Before we can paint to the screen, we need to determine the gesture that the user does creating an object using `GestureDetector.OnGestureListener`.

This object will override the onDown(MotionEvent) to return true since all motion inputs start with this input and if returns false, the input will ignored

According to the page, a simple on touch event won't be enough looking through the list of common event including tapping, pulling, pushing, flinging, and zooming, I believe pull would be the most effective for our needs because they would "pull the pen" with their fingers.

Another important fact for us is that we need to determine x and y coords to figure out how where to draw to and since we don't necessary want a constant straight line, it would be necessary to constantly update until the event has terminated. We would need to determine how many times we check so we could get a consistent line. This would only be necessary in regards to figure drawing since the lines using the joy sticks are all relatively straight.

For some examples of code that draw lines to the screen: 
- https://stackoverflow.com/questions/27321178/android-drawing-a-line-to-follow-your-finger
- https://medium.com/@puruchauhan/draw-line-on-finger-touch-c758a7bb59ac
The Framework the defines Canvas and Paint: https://developer.android.com/reference/android/graphics/package-summary