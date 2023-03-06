/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dataset: List<Dog> = DataSource.dogs

    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val imageView: ImageView? = view?.findViewById(R.id.dog_image)
        val nameTextView: TextView? = view?.findViewById(R.id.dog_name)
        val ageTextView: TextView? = view?.findViewById(R.id.dog_age)
        val hobbiesTextView: TextView? = view?.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        var adapterLayout: View? = null
        when (layout) {
            Layout.VERTICAL -> adapterLayout = from(parent.context).inflate(R.layout.activity_vertical_list, parent, false)
            Layout.HORIZONTAL -> adapterLayout = from(parent.context).inflate(R.layout.activity_horizontal_list, parent, false)
            Layout.GRID -> adapterLayout = from(parent.context).inflate(R.layout.activity_grid_list, parent, false)
        }
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: DogCardAdapter.DogCardViewHolder, position: Int) {
        val dog = dataset[position]
        holder.imageView?.setImageResource(dog.imageResourceId)
        holder.nameTextView?.text = dog.name
        holder.ageTextView?.text = dog.age

        val resources = context?.resources
        holder.hobbiesTextView?.text = resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
