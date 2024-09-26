# DACD-Final-Proyect
## Desarrollo de Aplicaciones para Ciencia de Datos
### Ciencia e Ingeniería de Datos
### Escuela de Ingeniería Informática     ULPGC


## Functionality
This program uses data provided by an API to obtain the weather forecast for 12pm of the following 5 days, in the 8 Canary Islands.
The data obtained is sended to an ActiveMQ broker. The information of the broker is updated every 6 hours.
The program also contains a module to read the messages in the broker and write the information in a .events file.
It makes use of a second API to obtain information about places around the locations provided.

## Resources
The program was entirely written in IntelliJ Idea, by Jet Brains.
The programming language of this program is Java, version 17.
The control version system used in developing this program is Git, and GitHub for its remote storage.
The API RESTs consumed in this program are 5 Day / 3h Forecast, by OpenWeatherMap, and Places by Foursquare.

## Design
We can observe the proyect is composed by 4 different modules.
Prediction-Provider and Places-Provider share a similar structure, in which main class creates the API provider, 
the ActiveMQSender, and the controller. This way the API is consumed every 6 hours, and information is saved in
ActiveMQ localhost.
Another module is the Datalake-builder. It funtions by reading the information displayed in our ActiveMQ, 
giving it a format to transform from JSON, and be saved in a text file.
Finally, PlacesBusinessUnit is in charge of the functionality, combining the information of weather and local places
to make recommendations depending on which place currently has the best weather out of the 8 islands, given certain parameters.
