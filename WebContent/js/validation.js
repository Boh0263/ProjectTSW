/*
Il campo password deve necessariamente avere:
 - almeno un carattere alfabetico minuscolo
 - almeno un carattere alfabetico maiuscolo
 - almeno un carattere numerico
 - almeno un carattere speciale, come % ^ & * ! @ # $
 - una lunghezza compresa tra 8 e 16
*/

//Togliere l'alert e sostituirlo con un indicazione attorno al campo assieme al messaggio sotto
//Una cosa del genere anche per la password
//volendo la stessa cosa anche sul modulo per aggiungere i prodotti (ed eventuali altri?)

// let float = document.getElementById("prezzo");
// let floatVal = float.value
// const regFloat = /^[+-]?\d+(\.\d+)?$/
// if(!regFloat.test(floatVal))

function validateUsername(username)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(username)) {
		return false;
	}
	/*
	if(username.value.match(letters))
	{
		return true;
	}
	else
	{	
		alert('Lo username può contenere soltanto caratteri alfabetici');
		name.focus();
		return false;
	}
	*/
}

function validatePassword(password)
{
	const pattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
	if(!pattern.test(password))
	{
		return false;
	}
}

function validateNomeProdotto(name)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(name))
	{
		return false;
	}
}

function validateGiacenza(giacenza)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(giacenza))
	{
		return false;
	}
}

function validateDescrizione(desc)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(desc))
	{
		return false;
	}
}

function validatePrezzo(prezzo)
{
	const pattern = /^[0-9]{1,3}(\.[0-9]{3})*(,\d{1,2})?€$|^,(\d{1,2})€$/;
	if(!pattern.test(prezzo))
	{
		return false;
	}
}

function validateIVA(iva)
{
	const pattern = /^\d{2}$/;	//esattamente due cifre
	if(!pattern.test(iva))
	{
		return false;
	}
}

function validateTipo(tipo)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(tipo))
	{
		return false;
	}
}

function validateGenere(genere)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(genere))
	{
		return false;
	}
}

function validateMateriale(mat)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(mat))
	{
		return false;
	}
}

function validatePezzo(pezzo)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(pezzo))
	{
		return false;
	}
}

function validateUtilizzo(usage)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(usage))
	{
		return false;
	}
}