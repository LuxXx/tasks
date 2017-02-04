module.exports = reduction;

const words = require('./words.js');

function reduction(hash) {

	if (typeof hash !== 'string') {
		console.log('input hash is not a string');
		return;
	}

	hash = hash.replace(/[a-z]/g,'');
	hash %= words.length;
	return words[hash];
}
