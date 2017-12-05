using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using PokedexNET;

namespace Pkemon
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class Page1 : ContentPage
	{
		public Page1 (Pokemon pokemon)
		{
			InitializeComponent ();
            var pokedex = new Pokedex();
            var info = pokedex.GetPokemonInfo(pokemon);

            name.Text = info.Names.En;
            we.Text = info.WeightEu;
            he.Text = info.HeightEu;
            des.Text = info.PokedexEntries.OmegaRuby.En;
            image.Source = ImageSource.FromUri(new Uri(pokemon.ImageUrl));

		}
	}
}