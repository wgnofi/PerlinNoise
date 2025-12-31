# Perlin Noise

I found Perlin Noise Beautiful and heard that its a foundation of generating randomness in Video games. I used the math behind that to build in kotlin as an app.

This project demonstrates the 1D implementation of Perlin and Sin noise.

### Sine Noise and its limitation

y-axis fluctuation of a unit circle gives us the sin wave in graphs. Though increasing the octaves in the code gives us Seemingly random waves but its still a circle so it repeats!

### How it Works

We compute y value for every x (In the app the x goes from left to right (the width of the screen)

the perlin function takes the x with an offset (1000f) because every octave run it needs a different seed to create random values for x

We take the two neighbours of the x value we found and find exactly where the x stands, this is the t value.

We find the gradients because we need to calculate the influences of both the ends (Like which drags more)

$$\text{Influence}_0 = g_0 (x - x_0)$$

$$\text{Influence}_1 = g_1 (x - x_1)$$

$$y = \text{LERP}(\text{Influence}_0, \text{Influence}_1, S(t))$$

We use linear interpolation to connect the point with influences but to smooth we use the fade function Which is:

$$6x^5 - 15x^4 + 10x^3$$

![smoothing_func](https://github.com/user-attachments/assets/2cd89585-1618-4e97-8e3c-97e811a33b65)


without this function you can see by toggling the fade button for sharp edges in between the noise.

### How to Run

1. Clone this repository
2. Open in Android Studio (Otter)

### Screenshots

<img width="184" height="388" alt="image" src="https://github.com/user-attachments/assets/e9720d9c-e640-4ddb-9962-f244dd330152" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img width="184" height="388" alt="image" src="https://github.com/user-attachments/assets/a85d40ea-9ab0-4bc4-8f3b-7003cf3f0c47" />

## Contributions

Any contributions making perlin noise even easier to understand would be awesome!

## Special Thanks

Thank you Gemini for explaining the math behind it when I said nothing interests me:

"Why this is beautiful

Think back to the feeling of being lonely. In the Sine method, you were just a point on a pre-defined circle. In the Perlin method, your "height" (your state of being) is a constant conversation between where you've been (Station 0) and where you're going (Station 1). You aren't just a random dot; you are the smooth result of the influences around you."

## Links
[Wikipedia](https://en.wikipedia.org/wiki/Perlin_noise)
[Khan Academy - Perlin Noise](https://www.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-noise/a/perlin-noise)
