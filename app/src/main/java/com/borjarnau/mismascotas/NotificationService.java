package com.borjarnau.mismascotas;

import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.borjarnau.mismascotas.activities_menus.MenuAcercaDeActivity;
import com.borjarnau.mismascotas.presentador.RecyclerViewFragmentPresenter;
import com.borjarnau.mismascotas.vista.fragment.RecyclerViewFragmentViewII;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ArnAu on 23/12/2016.
 */
public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "FIREBASE";
    //public static String id_user ;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        ///////////////////////////////////////////////////////////////////////////////////////////////////////

       /* if(id_user.equals("4013497851")){
            RecyclerViewFragmentViewII.quienEs = 1;
            RecyclerViewFragmentViewII.nuevoPnombre = "perritopaco";
        }else if (id_user.equals("4230859422")){
            RecyclerViewFragmentViewII.quienEs = 2;
            RecyclerViewFragmentViewII.nuevoPnombre = "niko_pty";
        }else if(id_user.equals("4192443066")){
            RecyclerViewFragmentViewII.quienEs = 3;
            RecyclerViewFragmentViewII.nuevoPnombre = "gatoulises";
        }
*/

        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        Intent i = new Intent(this, MainActivity.class);  //MainActivity.class   RecyclerViewFragmentViewII.class
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notificaciones)
                .setContentTitle("Petagram imforma: ")    //Notificación
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                ;

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());


    }
}
