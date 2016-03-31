
import java.lang.Math

// for the record, this wouldn't work at all since VOLK has no way
// of calling this function. It's also been a few months since I've
// written working java and haven't tested or compiled this code in
// any way. I did my best to make it "real" java while I hit my
// ballmer peak (if you write java on your ballmer peak does that
// make it a Joy peak?)
// Even if it did work, this would be stupid to do.
public static void volk_32fc_magnitude_32f_jazelle_fancy_sweet_32f(
        float* magnitudeVector,
        static lv_32fc_t* complexVector,
        unsigned int num_points)
{
  unsigned int number;
  unsigned int quarter_points = num_points / 4;

  static float scale = 0.4142135;
  static float aHigh = 0.84;
  static float aLow = 0.99;
  static float bHigh = 0.561;
  static float bLow = 0.197;

  // Despite advocates claiming the amazing standardized libraries
  // for Java, there is no standard complex numbertype and all of
  // the complex type libraries are incompatible. So instead we
  // just know that the C/C++ ABI is giving us interleaved I/Q.
  float realAbs;
  float imagAbs;

  float Min;
  float Max;

  for (int number = 0; number < quarter_points; number+=2)
  {
    // Ahhhhh, fancy and sweet in its simplicity
    realAbs = Math.abs(complexVector[number]);
    imagAbs = Math.abs(complexVector[number+1]);
    Max = Math.max(realAbs, imagAbs);
    Min = Math.min(realAbs, imagAbs);

    if ( Min <=  scale*Max )
    {
      magnitudeVector[number/2] = aLow*Max + bLow*Min
    }
    else
    {
      magnitudeVector[number/2] = aHigh*Max + bHigh*Min
    }
  }
}
