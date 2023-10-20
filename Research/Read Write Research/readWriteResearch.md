# Read/Write Research
By Jacob Dimoff

## Goal
Due to my previous research into saver states, I have determined that we can't save object attributes as objects that persist through process death since the objects themselves are stored in RAM. The leaves are used with two options, a database to store the values, or simply writing to a file. Although We aren't too worried about our apps overhead, I think reading/writing would be better because it means we don't have to make a database.

The goal of this research is to determine how we can read/write files in Kotlin, how to set it so that the user can't manipulate the files, and if we can't stop them, determine the best way to check to see if we have the values necessary to take the data from that file, input it into a constructor, and run the object so that it looks like the previous object that was deleted when app's process ended.

So at this point, I was sick and tired of trying to find some library to write to a file. turns out the solution is to just use Java. All we need to do is import the `Java.io` stream for files and then write the data sets of the object we want to save as a string and send it to the file. Then pull that data and reinstate an object with those same values when we come back. We will need to check if the file exists and if it has the proper data, but I think is our best option because it's quick and dirty.