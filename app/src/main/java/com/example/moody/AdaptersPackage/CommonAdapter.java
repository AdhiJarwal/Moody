package com.example.moody.AdaptersPackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moody.DescriptionActivity;
import com.example.moody.MainActivity;
import com.example.moody.R;
import com.example.moody.RegisterActivity;
import com.example.moody.SelectMoodActivity;
import com.example.moody.TtileDescGetterSetter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.MissingResourceException;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.viewHolders>
{
    ArrayList<TtileDescGetterSetter> list;
    Context context;
    String userNameAndPass;
    static String mood_remove;
    public static String key;

    public CommonAdapter(ArrayList<TtileDescGetterSetter>list,Context context)
    {
        this.list=list;
        this.context=context;
        if(!RegisterActivity.usernamePlusPass1.equals("Sakshi"))
        {
            userNameAndPass = RegisterActivity.usernamePlusPass1;
        }

        if(!MainActivity.loginUserId.equals("Adhiraj"))
        {
            userNameAndPass = MainActivity.loginUserId;
        }
        /********************************************************************************************/
        if(SelectMoodActivity.Happy_remove.equals("Happy"))
        {
            mood_remove=SelectMoodActivity.Happy_remove;
        }
        if(SelectMoodActivity.Sad_remove.equals("Sad"))
        {
            mood_remove=SelectMoodActivity.Sad_remove;
        }
        if(SelectMoodActivity.Angry_remove.equals("Angry"))
        {
            mood_remove=SelectMoodActivity.Angry_remove;
        }
        if(SelectMoodActivity.Other_remove.equals("Other"))
        {
            mood_remove=SelectMoodActivity.Other_remove;
        }
    }
    @NonNull
    @Override
    public viewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_to_display,parent,false);
        return new CommonAdapter.viewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolders holder, final int position)
    {

        TtileDescGetterSetter ttileDescGetterSetter=list.get(position);
        holder.textView12.setText(ttileDescGetterSetter.getTitle());

    }



    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class viewHolders extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {

        TextView textView12 ;
        public viewHolders(@NonNull View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            textView12 = itemView.findViewById(R.id.textView12);
        }

        @Override
        public void onClick(View v)
        {
            int position=this.getAdapterPosition();
            TtileDescGetterSetter ttileDescGetterSetter =list.get(position);


            Intent intent = new Intent(context, DescriptionActivity.class);
            key=ttileDescGetterSetter.getDescription();

            intent.putExtra("Title", ttileDescGetterSetter.getTitle());
            intent.putExtra("Description", ttileDescGetterSetter.getDescription());

            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v)
        {
            int position=this.getAdapterPosition();
            new AlertDialog.Builder(context)
                    .setTitle("Remove")
                    .setMessage("Are you sure to delete this message")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {

                            TtileDescGetterSetter ttileDescGetterSetter=list.get(position);
                            Toast.makeText(context,"Item Removed Successfully",Toast.LENGTH_LONG).show();
                            list.remove(position);
                            notifyItemRemoved(position);
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Query applesQuery = ref.child("Data").child(userNameAndPass).child(userNameAndPass+mood_remove).orderByChild("description").equalTo(ttileDescGetterSetter.getDescription());

                            applesQuery.addListenerForSingleValueEvent(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot)
                                {
                                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren())
                                    {
                                        appleSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError)
                                {

                                }
                            });


                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            }).show();

            return true;
        }
    }
}
