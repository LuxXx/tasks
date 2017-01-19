// The viterbi algorithm to find the sequence that appears the most
// https://en.wikipedia.org/wiki/Viterbi_algorithm
// This file was written while I was doing my exercises for an artificial intelligence class
// See the pdf file (exercise 2e)

// Startwahrscheinlichkeiten
let start_probs = {
  // a: P(X_0=a)
  t: 0.5,
  f: 0.5
};

// Emissionswahrscheinlichkeiten
let e_probs = {
  // a_b: P(X_t=a|X_{t-1}=b)
  t_t: 0.7,
  t_f: 0.2,
  f_t: 0.3,
  f_f: 0.8
};

// Übergangswahrscheinlichkeiten
let probs = {
  // P(Y_t=a|X_t=b)
  a_t: 0.2,
  a_f: 0.3,
  c_t: 0.3,
  c_f: 0.2,
  g_t: 0.3,
  g_f: 0.2,
  t_t: 0.2,
  t_f: 0.3
};

// The sequence to search
let sequence = ['a', 'c', 'g', 't'];

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

for (let i = 0; i < 5; i++) {
  for (p in start_probs) {
    console.log('m'+i+ (p === 't' ? '+' : '-') +': ' + m(p, i));
  }
}

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

// P(X_0=x)
function prob_x0(x) {
  let p = start_probs[x];
  return p === undefined ? 0 : p;
}
// P(Y_t=y|X_t=x)
function prob_y_x(y, x) {
  let p = probs[y + '_' + x];
  return p === undefined ? 0 : p;
}
//P(X_t|X_{t-1})
function prob_xt_xtminus1(x, z) {
  let p = e_probs[x + '_' + z];
  return p === undefined ? 0 : p;
}

function m(x, t) {
  if (t === 0) {
    return prob_x0(x);
  }
  let arr = [];
  for (p in start_probs) {
    arr.push(p);
  }
  arr = arr.map((e) => prob_xt_xtminus1(x, e) * m(e, t - 1));
  return prob_y_x(sequence[t-1], x) * Math.max.apply(null, arr);
}
