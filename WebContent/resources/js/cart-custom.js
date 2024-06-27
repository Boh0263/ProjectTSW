class Carrello {
    constructor() {
        if (localStorage.getItem('prodotti') != null) {
            this.prodotti = new Map(Object.entries(JSON.parse(localStorage.getItem('prodotti'))));
        } else {
            this.prodotti = new Map();
            localStorage.setItem('prodotti', JSON.stringify(Object.fromEntries(this.prodotti)));
        }
    }

    checkProdotti() {
        return localStorage.getItem('prodotti') != null;
    }

    addProduct(prodotto) {
        if (this.checkProdotti()) {
            if (this.prodotti.has(prodotto)) {
                this.prodotti.set(prodotto, this.prodotti.get(prodotto) + 1);
            } else {
                this.prodotti.set(prodotto, 1);
            }
            localStorage.setItem('prodotti', JSON.stringify(Object.fromEntries(this.prodotti)));
            this.updateProductNumber();
            showBanner('Prodotto aggiunto al carrello!', 'green');
        }
    }

    removeProduct(prodotto) {
        if (this.checkProdotti()) {
            if (this.prodotti.has(prodotto)) {
                if (this.prodotti.get(prodotto) > 1) {
                    this.prodotti.set(prodotto, this.prodotti.get(prodotto) - 1);
                } else {
                    this.prodotti.delete(prodotto);
                    showBanner('Prodotto rimosso dal carrello!', 'red');
                }
                localStorage.setItem('prodotti', JSON.stringify(Object.fromEntries(this.prodotti)));
                this.updateProductNumber();
            }
        }
    }

    updateProductNumber() {
        if (this.checkProdotti()) {
            let num = 0;
            for (let value of this.prodotti.values()) {
                num += value;
            }
            localStorage.setItem('CartCounter', num);
        } else {
            localStorage.setItem('CartCounter', 0);
        }
    }
}

function sendCartToServlet() {
    var cartData = localStorage.getItem('prodotti');
    if (cartData) {
        var xhr = new XMLHttpRequest();
        
        xhr.open('POST', 'CarrelloControl', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.setRequestHeader('X-CSRF-TOKEN', document.querySelector('input[name="_csrf"]').value); 
        xhr.send('keys=' + encodeURIComponent(cartData));
        xhr.onload = function() {
            if (xhr.status == 200) {
                localStorage.removeItem('prodotti');
                showBanner('Carrello inviato con successo!', 'green');
            } else {
                showBanner('Errore durante l\'invio del carrello', 'red');
            }
        };
    } else {
        showBanner('Il carrello è vuoto.', 'red');
    }
}

function showBanner(message, color) {
	const banner = document.createElement('div');
	banner.className = 'notification-banner ' + type;
	banner.textContent = message;
	
	const container = document.getElementById('notification-container');
	container.appendChild(banner);
	
	setTimeout(() => {
		banner.classList.add('show');
	}, 10);
	
	setTimeout(() => {
		banner.classList.remove('show');
		setTimeout(() => {
			banner.remove();
		}, 500); // Tempo corrispondente alla transizione CSS
	}, 3000);
}