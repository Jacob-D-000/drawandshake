# Liams-activity-notes

android uses state machines to determine what page and processes to run in the app in kotlin a state is a activity 

they have built in methods that should used for running and changing between these states

## onCreate
this is called when a activity first starts and is used to load the xml file and any other information necessary for the app activity to work properly

## onStart
this is used to store all the user inactions like event listeners

## onResume
this function is used if the onPause is used

this is were the code needed to resume the activity will begin

## onPause
this will stop the activity in its curet state

this is were you would set a new activity to start 

still run in background

## onStop
this will stop the activity

this is were you would set a new activity to start

## onRestart
this function is used if the onStop is used

this is were the code needed to resume the activity will begin

## onDestroy
This will remove any data of the activity from the app

## source
https://developer.android.com/guide/components/activities/intro-activities

and chatGTP