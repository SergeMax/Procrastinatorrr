package com.example.procrastinator.view;


import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.procrastinator.R;
import com.example.procrastinator.model.Taches;
import com.example.procrastinator.tools.tools.GoFicheIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;



public class ListTachesAdapter extends RecyclerView.Adapter<ListTachesAdapter.ClientsViewHolder> {

    List<Taches> listeTaches;
    private SafeParcelable.Class ListeClients;

    GoFicheIntent.OnTonFragmentInteractionListener mListener ;

    public ListTachesAdapter(List<Taches> listeTaches, GoFicheIntent.OnTonFragmentInteractionListener _mListener) {
        this.listeTaches = listeTaches;
        mListener = _mListener;
    }

    public static class ClientsViewHolder extends RecyclerView.ViewHolder {
        TextView nomClient1;
        TextView prenomClient1;

        GoFicheIntent.OnTonFragmentInteractionListener mListener;

        public ClientsViewHolder(View itemView, GoFicheIntent.OnTonFragmentInteractionListener _mListener) {
            super(itemView);

            nomClient1 = itemView.findViewById(R.id.catTache);
            prenomClient1 = itemView.findViewById(R.id.titreTache);
            mListener = _mListener;



         /*   btnConsulter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Ici on envoie un message à notre activity
                    mListener.onTonFragmentInteraction("13");

                }
            });

        }

        public TextView getBtnConsulter() {
            return btnConsulter;
        }*/
        }
    }


    @Override
    public ClientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_taches, parent, false);
        ClientsViewHolder clientsViewHolder = new ClientsViewHolder(view, mListener);
        return clientsViewHolder;
    }

    @Override
    public void onBindViewHolder(final ClientsViewHolder holder, int position) {



        Taches taches = listeTaches.get(position);
        holder.nomClient1.setText(taches.getTitre());
        holder.prenomClient1.setText(taches.getUrgence());


//        holder.btnConsulter.setTag(""+listeTaches.get(position).getNum_tache());


    }

    @Override
    public int getItemCount() {
        return listeTaches.size();
    }

}