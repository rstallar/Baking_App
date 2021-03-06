/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.becca.bakingapp.org;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.becca.bakingapp.R;
import com.example.becca.bakingapp.RecipeDetail;

import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder>{

    private Context mContext;
    private ArrayList<RecipeClass> mRecipeList;

    public RecipeAdapter(Context context, ArrayList<RecipeClass> recipeList) {
        mContext = context;
        mRecipeList = recipeList;
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder recipeAdapterViewHolder, int position) {
        String recipeName = mRecipeList.get(position).getName();
        recipeAdapterViewHolder.getTextView().setText(recipeName);
    }

    @Override
    public int getItemCount() {
        if (mRecipeList == null) {
            return 0;
        } return mRecipeList.size();
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public RecipeAdapterViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.recipe_name_list);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPosition = getAdapterPosition();
                    RecipeClass currentRecipe = mRecipeList.get(clickPosition);

                    Intent intent = new Intent(mContext, RecipeDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("RecipeKey", currentRecipe); //todo replace with static
                    intent.putExtras(bundle);

                    mContext.startActivity(intent);
                }
            });
        }
        public TextView getTextView() { return textView; }
    }

    public void setRecipeInfo(ArrayList<RecipeClass> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}