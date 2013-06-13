var AIS = (function() {
	// Farben fuer den Color Iterator
	var AISShipColors = [ '#48AEFF', '#7FFFD4', '#C00054', '#87EF84',
			'#DBA7F8', '#EBC79E' ];

	// Latitude und Logitude Grenzen des Kartenausschnitts
	var top = 50.759427;
	var bottom = 50.715809;
	var left = 7.089307;
	var right = 7.155004;

	// Hoehe und Breite des Kartenausschnitts in pixel
	var height = 800; // pixel
	var width = 766;

	// Hoehe und Breite der Schiffe in pixel
	var shipHeight = 20;
	var shipWidth = 5;
	
	var longitudeToX = function(longitude, width) {		
		return (Number) ((this.width / 360.0) * (180 + longitude));	
	};
	
	var latitudeToY = function(latitude, height) {
		return (Number) ((this.height / 180.0) * (90 + latitude));	
	};	
	
	// Color iterator
	var colorIterator = function() {
		// Aufgabe 2
		var iterate = 0;

		return function() {
			var i = iterate;
			iterate = (iterate + 1) % AISShipColors.length;
			return AISShipColors[i];
		};
	};

	// Ship Konstruktor
	var Ship = function(mmsi, latitude, longitude, courseOverGround) {
		// Aufgabe 3
		this.mmsi = mmsi;
		this.latitude = latitude;
		this.longitude = longitude;
		this.courseOverGround = courseOverGround;
	};

	Ship.prototype.draw = function(canvas, color) {
		// Aufgabe 6
		
		var ctx = canvas.getContext('2d');
		var x = longitudeToX(this.longitude, width) - shipWidth / 2.0; // lower left x
		var y = latitudeToY(this.latitude, height) + shipHeight / 2.0; // lower left y	
		
		
		// path begin
		ctx.beginPath();

		// move to lower left corner
		ctx.moveTo(x, y);
		
		// left side
		ctx.lineTo(x, y - shipHeight * (2.0/3.0)); // left side: + 2/3 height

		// head
		ctx.lineTo(x + shipWidth / 2.0, y - shipHeight); // middle: + 1/3 height, + 1/2 width
		ctx.lineTo(x + shipWidth, y - shipHeight * (2.0/3.0)); //right side: - 1/3 height, + 1/2 width
		
		//right side
		ctx.lineTo(x + shipWidth, y);
		
		// from right to left
		ctx.lineTo(x, y);
		
		ctx.stroke();
		
		// |
		//   .
		//  /|\
		// / | \ ___2/3
		// | | |
		// --|--.--| ------
		// | | |
		// | | |
		// |-----|
		// |
		// |
	};

	// ShipList Konstruktor
	var ShipList = function() {
		this.list = []; // initialisieren eines Arrays zum aufnehmen der Ship
						// Objekte
	};

	// Aufgabe 4
	ShipList.prototype.filter = function(filterFunction) {
		// Erstellt ein neues, leeres Array und f�gt nur die validen Objekte,
		// welche
		// durch die filterFunction gepr�ft werden, der neuen Liste hinzu
		var newList = [];
		for (var i in this.list) {
			if (filterFunction(this.list[i]))
				newList.push(this.list[i]);
		}
		this.list = newList;
	};

	// Laden der Daten und Zeichnen aller Schiffe auf das uebergebene Canvas
	var drawAllShips = function(canvas) {

		// neue ShipList erstellen
		var currentShips = new AIS.ShipList();

		// fuer jeden Datensatz, ein Ship erstellen und in die ShipList legen
		for ( var i = 0; i < AISData.length; i++) {
			currentShips.list.push(new AIS.Ship(AISData[i].mmsi,
					AISData[i].latitude, AISData[i].longitude,
					AISData[i].courseOverGround));
		}

		// Filtern der Schiffe, die sich nicht auf dem Kartenausschnitt befinden
		currentShips.filter(function(ship) {
			return ship.longitude >= left && ship.longitude <= right
					&& ship.latitude >= bottom && ship.latitude <= top;
		});

		// neuen Color Iterator erstellen
		var colors = AIS.colorIterator();

		// alle Ships, mit jeweils der naechsten Farbe rendern
		for ( var i = 0; i < currentShips.list.length; i++) {
			var ship = currentShips.list[i];
			ship.draw(canvas, colors());
		};
	};

	return {
		Ship : Ship,
		ShipList : ShipList,
		drawAllShips : drawAllShips,
		colorIterator : colorIterator
	};

})(); 

document.addEventListener("DOMContentLoaded", function() {
	// Aufgabe 5
	var canvas = document.getElementById('map'); // get Element from the DOM
	var img = new Image();
	img.onload = function() {
		canvas.getContext('2d').drawImage(img, 0, 0);

		// Zeichnen sie zuerst die Karte (images/map.png) auf das canvas.
		// Anschliessend, lassen Sie alle Schiffe auf die Karte zeichnen:
		// AIS.drawAllShips(canvas);.
		
	};
	//img.src = 'images/map.png';
	AIS.drawAllShips(canvas);
}, false);
