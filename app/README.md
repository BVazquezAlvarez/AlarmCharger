AlarmCharger
¿Qué es?

¿Alguna vez te has sentido preocupado por dejar tu móvil cargando en un sitio público y que te lo pudieran robar? Alarmcharger es una sencilla para intentar que tu móvil esté seguro, con sus dos alarmas que se activan en cuanto el movil deje de recibir corriente eléctrica:

-Alarma normal: Un fuerte sonido multimedia suena durante 60 segundos en cuanto se desenchufe de la corriente.
-Alarma GPS: Nos localizará el movil durante 3 días. Nos mandará las coordenadas en donde esté nuestro móvil al correo cada hora.

¿Como funciona?
Clases

Alarm Charger está formado por 2 activities y 3 clases:

-Main Activity-> Es la pantalla principal de la aplicación, donde se arma y se desarma la alarme y desde donde se entra a las Opciones
-Opciones Activity-> Es la pantalla secundaria, donde elegiremos que tipo de alarma se activarán en cuanto se desconecte el dispositivo de la elecricidad
-Clase PowerConnectionReceiver-> Esta clase es la más importante de la aplicación. Desde aquí se detecta cuando hay un cambio en el estado de carga de batería (onReceive) y desde ahí empieza a sonar o no las alarmas, dependiendo de las que tengamos activadas y si la alarma general está armada. Los dos tipos de alarmas están rodeadas de sus respectivos timers:

     -El timer de la alarma normal dura 60 segundos y cada segundo se pregunta si la alarma general sigue armada, en cuanto deje de estarlo la alarma deja de sonar. (mediaPlayer)
     -El timer de la alarma GPS dura tres días y cada hora te manda una notificacion a tu correo (GMailSender) de donde está tu dispositivo utilizando las coordenadas (LocationManager registra tu última posición conocida).

-Clases GMailSender y JSSEProvider -> Una se encargará de la configuración del mail para su envio usando el protocolo STMP y la otra se encargará del cifrado y la conexión segura.

Librerías adicionales

Se han tenido que añadir tres librerías adicionales en la parte de envío del correo, ya que Android por defecto no deja enviar mensajes automáticos con el correo por defecto ni con otros. Estas librerías son completamente fiables:activation.jar, addicional.jar y mail.jar