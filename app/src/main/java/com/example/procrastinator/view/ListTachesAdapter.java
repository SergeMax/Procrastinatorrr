package com.example.procrastinator.view;


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

import static android.graphics.Color.rgb;


public class ListTachesAdapter extends RecyclerView.Adapter<ListTachesAdapter.ClientsViewHolder> {

    List<Taches> listeTaches;
    private SafeParcelable.Class ListeClients;

    GoFicheIntent.OnTonFragmentInteractionListener mListener ;

    public ListTachesAdapter(List<Taches> listeTaches, GoFicheIntent.OnTonFragmentInteractionListener _mListener) {
        this.listeTaches = listeTaches;
        mListener = _mListener;
    }

    public static class ClientsViewHolder extends RecyclerView.ViewHolder {
        TextView flag;
        TextView urgence;
        TextView categorie;
        TextView titre;

        GoFicheIntent.OnTonFragmentInteractionListener mListener;

        public ClientsViewHolder(View itemView, GoFicheIntent.OnTonFragmentInteractionListener _mListener) {
            super(itemView);

            categorie = itemView.findViewById(R.id.catTache);
            titre = itemView.findViewById(R.id.titreTache);
            urgence = itemView.findViewById(R.id.urgence);
            flag = itemView.findViewById(R.id.flag);

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
        holder.categorie.setText(taches.getCategorie());
        holder.titre.setText(taches.getTitre());
        holder.urgence.setText(taches.getUrgence());
        holder.flag.setAlpha(0.6f);

        switch(taches.getUrgence()) {
            case "1":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(245,253,169))));
                break;
            case "2":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(221,246,155))));

                break;
            case "3":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(175,246,155))));
                break;
            case "4":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(169,245,253))));
                break;
            case "5":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(169,203,253))));
                break;
            case "6":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(79,148,252))));
                break;
            case "7":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(251,136,54))));
                break;
            case "8":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(251,77,54))));
                break;
            case "9":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(150,19,2))));
                break;
            case "10":
                holder.flag.setBackgroundColor(Integer.parseInt(String.valueOf(rgb(50,6,0))));
                break;
            default:
                // code block
        }



//        holder.btnConsulter.setTag(""+listeTaches.get(position).getNum_tache());


    }

    @Override
    public int getItemCount() {
        return listeTaches.size();
    }

}