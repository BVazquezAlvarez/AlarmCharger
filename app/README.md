AlarmCharger
�Qu� es?

�Alguna vez te has sentido preocupado por dejar tu m�vil cargando en un sitio p�blico y que te lo pudieran robar? Alarmcharger es una sencilla para intentar que tu m�vil est� seguro, con sus dos alarmas que se activan en cuanto el movil deje de recibir corriente el�ctrica:

-Alarma normal: Un fuerte sonido multimedia suena durante 60 segundos en cuanto se desenchufe de la corriente.
-Alarma GPS: Nos localizar� el movil durante 3 d�as. Nos mandar� las coordenadas en donde est� nuestro m�vil al correo cada hora.

�Como funciona?
Clases

Alarm Charger est� formado por 2 activities y 3 clases:

-Main Activity-> Es la pantalla principal de la aplicaci�n, donde se arma y se desarma la alarme y desde donde se entra a las Opciones
-Opciones Activity-> Es la pantalla secundaria, donde elegiremos que tipo de alarma se activar�n en cuanto se desconecte el dispositivo de la elecricidad
-Clase PowerConnectionReceiver-> Esta clase es la m�s importante de la aplicaci�n. Desde aqu� se detecta cuando hay un cambio en el estado de carga de bater�a (onReceive) y desde ah� empieza a sonar o no las alarmas, dependiendo de las que tengamos activadas y si la alarma general est� armada. Los dos tipos de alarmas est�n rodeadas de sus respectivos timers:

     -El timer de la alarma normal dura 60 segundos y cada segundo se pregunta si la alarma general sigue armada, en cuanto deje de estarlo la alarma deja de sonar. (mediaPlayer)
     -El timer de la alarma GPS dura tres d�as y cada hora te manda una notificacion a tu correo (GMailSender) de donde est� tu dispositivo utilizando las coordenadas (LocationManager registra tu �ltima posici�n conocida).

-Clases GMailSender y JSSEProvider -> Una se encargar� de la configuraci�n del mail para su envio usando el protocolo STMP y la otra se encargar� del cifrado y la conexi�n segura.

Librer�as adicionales

Se han tenido que a�adir tres librer�as adicionales en la parte de env�o del correo, ya que Android por defecto no deja enviar mensajes autom�ticos con el correo por defecto ni con otros. Estas librer�as son completamente fiables:activation.jar, addicional.jar y mail.jar