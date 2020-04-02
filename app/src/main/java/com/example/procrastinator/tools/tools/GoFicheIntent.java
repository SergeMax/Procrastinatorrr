package com.example.procrastinator.tools.tools;

import android.content.Context;

import androidx.fragment.app.Fragment;

public class GoFicheIntent extends Fragment {

    //Un objet de type interface. On va l'utiliser pour passer des instructions à notre activity en appelant la méthode onTonFragmentInteraction(...)
    private OnTonFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTonFragmentInteractionListener) {
            //Ici, on affecte l'instance OnTonFragmentInteractionListener à notre objet mListener
            mListener = (OnTonFragmentInteractionListener) context;

            System.out.println(mListener + "mlisterner passe dans Onattach ");

        } else {
            throw new RuntimeException(context.toString() + " must implement OnTonFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        System.out.println(mListener + "mlisterner egale nul dans onDetach ");
    }

    public interface OnTonFragmentInteractionListener{
        //Par ce cette méthode le fragment va communiquer avec l'activity (lui demander dans notre cas de lancer une nouvelle activity tout en servant des données en paramètre de la méthode pour un traitement spécifique
        void onTonFragmentInteraction(String data);
    }
}
