# Spotify album image urls to AWS Kinesis.

Simple test application that fetches from the Spotify API on a scheduled basis and
writes to an AWS Kinesis stream for further processing.

Requires Java 11 and Apache Maven to build.

For the Spotify stuff to work you need to set to environment variables:

####SPOTIFY_ID=\<your spotify-id>
####SPOTIFY_SECRET=\<your spotify secret>

You also need to configure your AWS account to have the right access against 
the Kinesis instance.
