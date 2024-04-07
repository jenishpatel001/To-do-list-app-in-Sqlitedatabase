package Adapter;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.MainActivity;
import com.example.sqlite.R;

import java.util.List;

import Model.Todolist;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.Myholder> {
    List<Todolist> todolists;
    MainActivity activity;
    Cursor cursor;

    public TodoAdapter(Cursor cursor) {
        this.cursor = cursor;
    }


    @Override
    public TodoAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.task_layout,parent,false );
        Myholder h = new Myholder( itemView );
        return h;
    }

    @Override
    public void onBindViewHolder( TodoAdapter.Myholder holder, int position) {
        cursor.moveToPosition( position );
        holder.task.setText(cursor.getString( 0 ));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class Myholder extends RecyclerView.ViewHolder {
      CheckBox task;
        public Myholder(View itemview) {
            super( itemview );
            task = itemview.findViewById( R.id.todocheckbox0);
        }
    }
}
