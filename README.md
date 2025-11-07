# Why would I even drop an item now?!

- Usage:
**Shift + Right Click** looking at another player with your item in your hands, to get this item transferred for them!

<p align="center" href="http://www.youtube.com/watch?feature=player_embedded&v=n58SXbZyypg" target="_blank">
 <img src="http://img.youtube.com/vi/n58SXbZyypg/mqdefault.jpg" alt="Video Example for Take This" width="480" height="360" border="10" />
</p>

- Requirements:
  - Java 11 or higher (build time)
  - Java 8 or higher (runtime)

- Supported versions:
  - 1.20.x (default build target) → `TakeThis-1.20.x-1.0.jar`
  - 1.21.x (`mvn -P mc-1.21 package`) → `TakeThis-1.21.x-1.0.jar`

- Additional info:
 You may configure the plugin as you wish in the `TakeThis/config.json5` file.
 You may not mess with the `TakeThis/config.yml` file, because it is used to store opt-in and out variables.
 
- Known issues:
 Sometimes the variable ${item_quantity} will show wrong values in the chat, but items can be transferred correctly (i hope so).
