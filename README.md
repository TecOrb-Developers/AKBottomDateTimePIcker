# AKBottomDateTimePIcker

This is a simple AKBottomDateTimePIcker library that helps you to implement the bottom  time picker.
Bottom Date time picker is a dialog with the picking date and time in sliding form date as well as time both are rotating.

Add it in your root build.gradle at the end of repositories:
-----------------------------------------------------------
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add the dependency
------------------

	dependencies {
	        implementation 'com.github.TecOrb-Developers:AKBottomDateTimePIcker:0.0.1'
	}


Usage
-----

val bottomDateTimePicker: BottomDateTimePicker = BottomDateTimePicker.newInstance("Title")
bottomDateTimePicker.show(supportFragmentManager, "SomeTAG")
bottomDateTimePicker.setOnDateTimeSetListener(object :
BottomDateTimePicker.OnDateTimeSetListener {
override fun onDateTimeSelected(selectedDateTime: Calendar?) {
something.text = selectedDateTime?.let { it.time.toString()}
}
})



        
