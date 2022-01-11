package es.android.paises;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.android.paises.placeholder.PlaceholderContent.Pais;
import es.android.paises.databinding.FragmentPaisBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Pais}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PaisRecyclerViewAdapter extends RecyclerView.Adapter<PaisRecyclerViewAdapter.ViewHolder> {

    private List<Pais> mValues;
    List<Pais> copialista;

    public PaisRecyclerViewAdapter(List<Pais> items) {

        mValues = items;
        copialista = new ArrayList<>();
        copialista.addAll(mValues);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPaisBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    public void filter(String text){


            mValues.clear();
            if(text.isEmpty()){
                mValues.addAll(copialista);
            } else{
                text = text.toLowerCase();
                for(Pais item: copialista){
                    if(item.nombre.toLowerCase().contains(text) ){
                        mValues.add(item);
                    }
                }
            }
            notifyDataSetChanged();

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).nombre);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mContentView;
        public Pais mItem;

        public ViewHolder(FragmentPaisBinding binding) {
            super(binding.getRoot());
            mContentView = binding.content;
            binding.getRoot().setOnClickListener(this);
        }


        @Override
        public String toString() {

            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putParcelable("pais", mItem);
            args.putString("title", mItem.nombre);

            Navigation.findNavController(v)
                    .navigate(R.id.action_paisesFragment_to_detallePaisFragment, args);
        }
    }
}