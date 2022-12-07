package iesmm.pmdm.recyclerview_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static  class  ViewHolder extends  RecyclerView.ViewHolder{
        private TextView pais,ciudad;
        ImageView fotoPaisaje;

        public ViewHolder(View itemView) {
            super(itemView);
            this.pais =(TextView) itemView.findViewById(R.id.textPais);
            this.ciudad =(TextView)itemView.findViewById(R.id.txtCiudad) ;
            this.fotoPaisaje =(ImageView) itemView.findViewById(R.id.imgPaisaje) ;
        }
    }

    public List<PaisajeModelo> paisajeLista;

    public RecyclerViewAdapter(List<PaisajeModelo> paisajeLista) {
        this.paisajeLista = paisajeLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paisaje,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pais.setText(paisajeLista.get(position).getPais());
        holder.ciudad.setText(paisajeLista.get(position).getCiudad());
        holder.fotoPaisaje.setImageResource(paisajeLista.get(position).getImgPaisaje());
    }

    @Override
    public int getItemCount() {
        return paisajeLista.size();
    }
}
