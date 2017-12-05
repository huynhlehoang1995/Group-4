using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using PokedexNET;

namespace Pkemon
{
	public partial class MainPage : ContentPage
	{
        private Pokedex _pokedex = new Pokedex();
        private Pokemon pokemon= new Pokemon();
		public MainPage()
		{
			InitializeComponent();
		}
        private void Button_clicked(object sender, EventArgs e)
        {
            var text = txtNumber.Text;
            int number = int.Parse(text);

                pokemon = _pokedex.GetPokemon(number);

                lblName.Text = pokemon.Name;
                imgImage.Source = ImageSource.FromUri(new Uri(pokemon.ImageUrl));
           
        
        }

        private void Button2_clicked(object sender, EventArgs e)
        {
            if(pokemon != null)
            {
                Navigation.PushAsync(new Page1(pokemon));
            }
        }
    }
}
