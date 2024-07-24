## Description

Hi there!, this is an an assignment project for openInApp.

## Tech Stack / Libraries Used
- Kotlin
- MVVM Architecture
- Observer Pattern
- Retrofit for networking
- Glide for Image Loading
- ViewBinding

## Working App Video
https://github.com/user-attachments/assets/69353f29-a7b0-4c6b-9e6d-0c57600faa7d


## Steps to run the app
1. Clone the project

2. In order to run the app you need to add your bearer token in the local.properties in the root directory
local.properties

```
sdk.dir=
BEARER_TOKEN = some token of dev staging // make sure no " " quotes are wrapped.
```
or 
simply paste the code in APIService

```
interface APIServices {

    @GET("dashboardNew")
    @Headers(
        "Authorization: Bearer yourbearertoken"
    )
    fun getDashboard() : Call<ApiResponse>
}
```
