# TransferWise Code Challenge

This app challenge was to design and develop a quick app designed to showcase my Android skills. I decided to approach with the intent of creating an Application that went beyond the specifications for the challenge. This App is designed to:
   - Allow a user to login to their Spotify Profile and see their saved playlists
   - Allow a user to tap a playlist to see all the available tracks within the playlist
   - Allow a user to tap a track to view the Album Artwork 
   - Allow the user to swipe through their playlist tracks from the Album Artwork View (Swipe Left or Right)

Tapping on any image item should allow the user to start playing the track (Premium Spotify Accounts Only).
Tapping the image again will transition between resume/pause functionality

## Running The App
- Spotify Authentication Requires an OAuth2 Token for accessing the data
	- This required me to create a unique signing fingerprint for both debug and release versions of the app.
	- I've included the signed release apk file in the zip. You should be able to install this apk onto any android device running Lollipop 5.0 (SDK 21) or higher to use the app if there are any complications from using the source code.
	- If there are still issues, please contact me and I will work on adding a new fingerprint to communicate with Spotify.

## Structure of App
- The project structure is grouped based on functionality and structured as follows:
	- Activity (Contains all Activity Views)
	- Components (Contains Interfaces Used For Dependency Injection)
	- Fragment (Contains all Fragment Views)
	- Model (Contains all POJOs for Object Mapping)
	- Modules (Contains implementation of dependencies used for injection)
	- Network (Contains Networking Stack For RESTful HTTP Requests/Responses)
	- Spotify (Contains Controller for Spotify Services)
	- Store (Contains Data Store for Caching And RecyclerView Adapters)
- I utilized Dependency Inversion (through Dependency Injection) when possible using Dagger 2 to keep the design as modular as possible.
- I decided to abstract as much of the implementation as possible from the views by creating the SpotifyService class and RestInvoker interface. It decoupled the fundamental logic for establishing the view from the views themselves so I could perform proper unit testing
- I decided to create the networking stack from scratch using only Spring's Resttemplate. This allows for more flexibility for the implementation of the networking stack and showcased my understanding of Java Reflection [Yay Reflection!](http://tv.giphy.com/yay) 
- I decided to design an approach that persists the network calls for the playlists and track images in internal memory to allows caching the data in the event the network dropped 
	- This required me to take into consideration memory allocation, especially for older devices. 
	- I accomplished this by creating a shared cache within the current session that will remove its contents whenever the application receives a low memory warning from the OS
 
## Challenges 
- Resttemplate and Caching
	- One caveat from this implementation is that Resttemplate does not support caching network responses out of the box...[Boo](http://tv.giphy.com/sad)
	- Ideally one could use a different HTTPClient that supports caching 
		- [OKHttp3](https://github.com/square/okhttp)
		- [Retrofit2](http://square.github.io/retrofit/)
		- [Volley](https://github.com/google/volley)
	- However, I was able to implement my own caching mechanism that accomplishes the scope of this code challenge.

- Unit Testing With Mockito/Roboeletric
	- While I do have working and functional unit tests for most of my project, I tried to complete the mocking of the AsyncTask within the RestInvoker with Mockito and Roboeletric 
		- However it failed to properly initialize the response object returning null with every implementation I tried. Given more time I'd like to properly mock that AsyncTask object out by striping the AsyncTask from the implementation and creating it through an abstract factory that I can mock within the RestInvokerClient's unit tests.

## Third Party Frameworks
I used the following Third Party Frameworks in the app:

- [Spring For Android](http://projects.spring.io/spring-android/) - For Networking
- [Jackson2](https://github.com/FasterXML/jackson) - For Object Mapping
- [Dagger2](https://google.github.io/dagger/) - For Dependency Injection
- [Butterknife](http://jakewharton.github.io/butterknife/) - For View Bindings
- [JodaTime](https://github.com/dlew/joda-time-android) - For Accurate Timing on the Track Duration
- [Mockito](https://github.com/mockito/mockito) - For Mocking Objects For Unit Testing
- [Roboleric](http://robolectric.org/) - For Unit Testing

