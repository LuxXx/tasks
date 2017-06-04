function Partition(a, b, N) {
  // a the lower bound of the interval
  this.a = a;
  // b the upper bound of the interval
  this.b = b;
  // N the amount of subintervals of this partitions
  this.N = N;

  // returns the x value for the nth subinterval
  this.x = function (n) {
    let len = this.b - this.a;
    let z = len / this.N;
    return this.a + n * z;
  }
  // integrates over f using the riemann sum with N subintervals with g as a norm
  this.integrate = function (f, g) {

    if (g === undefined)
      g = x => x;

    let sum = 0;
    for (let i = 1; i < this.N; i++) {
      sum += f(this.x(i)) * g((this.x(i)) - g(this.x(i - 1)));
    }
    return sum;
  }
}

// Example
let a = new Partition(0, 40, 100000).integrate(x => x*x, x => x);
console.log(a);
