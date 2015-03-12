package com.ftfl.icareapplication.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.ProfileDataSource;
import com.ftfl.icareapplication.model.Profile;





@SuppressLint("NewApi")
public class CreateProfileFragment extends Fragment{

	/*//ProfileDataSource mProfileDS = null;
		List<Profile> mProfileList = new ArrayList<Profile>();
		List<String> mNamesList = new ArrayList<String>();
		List<String> mIdList = new ArrayList<String>();
		ListView mListView = null;
		Integer mPosition = 0;
		ImageView imgEmergencyCall,mAbout;
		Context thiscontext;
		@SuppressLint("NewApi")
		@Override
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_create_profile, container, false);
			
			ProfileDataSource profiledata = new ProfileDataSource(this.thiscontext);
			mProfileList = profiledata.iCareProfilesList();
			for (int i = 0; i < mProfileList.size(); i++) {
				Profile mProfile = mProfileList.get(i);
				mIdList.add(mProfile.getId());
				mNamesList.add(mProfile.getName());}
			
			mListView = (ListView) rootView.findViewById(R.id.lv_profilelistview1);
			
			ArrayAdapter<String> mProfileAdapter = new ArrayAdapter<String>(this.getActivity(),
			        android.R.layout.simple_list_item_1, mNamesList);
			
			mListView.setAdapter(mProfileAdapter);
	        return rootView;
		}
		private void setListData() {
			
			ProfileDataSource profiledata = new ProfileDataSource(thiscontext);
			mProfileList = profiledata.iCareProfilesList();
			for (int i = 0; i < mProfileList.size(); i++) {
				Profile mProfile = mProfileList.get(i);
				mIdList.add(mProfile.getId());
				mNamesList.add(mProfile.getName());
			}
			
		}
//		public void viewData(Integer ePosition)
//		{
//			Intent mEditIntent = new Intent(getApplicationContext(),
//					DiseViewHomeActivity.class);
//			Long id = Long.parseLong(mIdList.get(ePosition));
//			mEditIntent.putExtra("id", id.toString());
//			startActivity(mEditIntent);
//		}
//		public void editData(Integer ePosition)
//		{
//			Intent mEditIntent = new Intent(getApplicationContext(),
//					CreateProfileActivity.class);
//			Long id = Long.parseLong(mIdList.get(ePosition));
//			mEditIntent.putExtra("id", id.toString());
//			startActivity(mEditIntent);
//			
//		}*/
	 
}
