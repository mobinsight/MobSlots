package com.mobinsight.slotmachine;

import com.mobinsight.slotmachine.surveys.SurveyActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class SlotMachineDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.app_name)
		.setMessage(R.string.betmaxmessage)
		.setPositiveButton(R.string.mobinsight, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Log.i("SlotMachine", "Starting the survey activity");
				Intent surveyIntent = new Intent(getActivity(), SurveyActivity.class);
				surveyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(surveyIntent);
			}
		})
		.setNegativeButton(R.string.buycredits, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent buyCreditsIntent = new Intent(getActivity(), BuyCredits.class);
				buyCreditsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(buyCreditsIntent);
			}
		});
		
		Dialog dialog = builder.create();
		dialog.show();
		return dialog;
		
	}
}
