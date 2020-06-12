package com.android.countriesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.countriesapp.R
import com.android.countriesapp.domain.countries.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter(
    private val countries: List<Country>
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(
            inflater.inflate(
                R.layout.item_country,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country) {
            itemView.countryName.text = country.name
            itemView.countrySubregion.text = country.subregion
            itemView.countryArea.text = country.area.toString()
            itemView.countryPopulation.text = country.population.toString()
        }
    }
}