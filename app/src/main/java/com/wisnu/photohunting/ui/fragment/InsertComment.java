package com.wisnu.photohunting.ui.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;
import com.wisnu.photohunting.system.Utils;

import retrofit.Callback;
import retrofit.Retrofit;

public class InsertComment extends DialogFragment implements View.OnClickListener
{
    private static InsertComment instance = new InsertComment();

    public static InsertComment newInstance (String pid, String uid)
    {
        Bundle argument = new Bundle();
        argument.putString("PID", pid);
        argument.putString("UID", uid);
        instance.setArguments(argument);
        return instance;
    }

    private EditText edMessage;

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.partial_insert_comment, container, false);
        edMessage = (EditText) view.findViewById(R.id.new_message);
        TextView tvSubmit = (TextView) view.findViewById(R.id.submit);
        tvSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick (View v)
    {
        String pid     = this.getArguments().getString("PID");
        String uid     = this.getArguments().getString("UID");
        String message = edMessage.getText().toString();
        Utils.showOnConsole("InsertComment", "onClick : PID, UID, Message : " + pid + " | " + uid + " | " + message);

        Request.Photo.add_comment(message, pid, uid).enqueue(new Callback<Response.Basic>()
        {
            @Override
            public void onResponse (retrofit.Response<Response.Basic> response, Retrofit retrofit)
            {
                if (response.body().getData() != null)
                {
                    if (!response.body().getStatus().equals("false"))
                    {
                        Utils.showToast(getActivity(), "Berhasil menambahkan komentar");
                        dismiss();
                    } else onFailure(new Throwable("Gagal menambahkan komentar"));
                } else onFailure(new Throwable("Gagal menambahkan Komentar"));

                dismiss();
            }

            @Override
            public void onFailure (Throwable t)
            {
                Utils.showOnConsole("InsertComment", "onFailure : " + t.getLocalizedMessage());
            }
        });
    }
}
