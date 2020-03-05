
# Bing for Commerce Java SDK

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.microsoft.bing/bing-commerce-search/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.microsoft.bing/bing-commerce-search) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.microsoft.bing/bing-commerce-ingestion/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.microsoft.bing/bing-commerce-ingestion)


## Overview

This contains the generated SDKs that can help developers integrate with Bing for Commerce platform, both on the Search and Ingestion sides. The repository also contains unit tests and samples that can show you quick examples for how to use the SDKs.

For more details about the project, please refer to the [Bing for Commerce main repository](https://github.com/microsoft/bing-commerce), or [Bing for Commerce API Documentation](https://commerce.bing.com/docs/product-search/).

## Getting Started

### Prerequisites

* [Java Development Kit (JDK) with version 8 or above](https://docs.microsoft.com/en-us/java/azure/jdk/java-jdk-install?view=azure-java-stable).
* [Apache Maven](https://maven.apache.org/).
* [Bing for Commerce Account](https://commerce.bing.com/).

### Adding the package to your product

Both search and ingestion SDKs are published to the Maven central repository, you just need to add the dependencies in your pom file, and you'll be able to start using it.

#### Seach package

~~~xml
<dependency>
  <groupId>com.microsoft.bing</groupId>
  <artifactId>bing-commerce-search</artifactId>
  <version>1.0.0</version>
</dependency>
~~~

#### Ingestion package

~~~xml
<dependency>
  <groupId>com.microsoft.bing</groupId>
  <artifactId>bing-commerce-ingestion</artifactId>
  <version>1.0.0</version>
</dependency>
~~~

### Authenticate the client

Bing for Commerce APIs use Bearer Tokens for authentication. You can use the [Bing for Commerce Portal Documentation](https://commerce.bing.com/docs/Portal%20Documentation/#manage-keys-and-tokens) for help creating one.

## Usage

## Imports

You will need to add imports for the required implementation as part of the sdk, besides any other dependencies needed.

#### Ingestion imports:
~~~java
import com.microsoft.bing.commerce.ingestion.BingCommerceIngestion;
import com.microsoft.bing.commerce.ingestion.implementation.BingCommerceIngestionImpl;
import com.microsoft.bing.commerce.ingestion.models.*;
import com.microsoft.bing.commerce.ingestion.util.AccessTokenInterceptor;
import com.microsoft.bing.commerce.ingestion.util.AccessTokenProvider;
~~~

#### Search imports:
~~~java
import com.microsoft.bing.commerce.search.BingCommerceSearch;
import com.microsoft.bing.commerce.search.implementation.BingCommerceSearchImpl;
import com.microsoft.bing.commerce.search.models.*;
import com.microsoft.bing.commerce.search.util.AccessTokenInterceptor;
import com.microsoft.bing.commerce.search.util.AccessTokenProvider;
~~~

### Create the SDK client object

Creating the SDK client object SDK are the first step you need to do in order to call the Bing for Commerce services APis. You will need first to get an access token with the proper access scope as described [here](https://commerce.bing.com/docs/Portal%20Documentation/#manage-keys-and-tokens).

#### Create the Ingestion SDK Client
~~~java
private BingCommerceSearch createSearchClient(final String accessToken) {
    
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(new AccessTokenInterceptor( new SimpleAccessTokenProvider(accessToken) ));
    Retrofit.Builder retrofit = new Retrofit.Builder();

    return new BingCommerceSearchImpl(httpClient, retrofit);
}
~~~

#### Create the Search SDK Client
~~~java
private BingCommerceIngestion createSearchClient(final String accessToken) {
    
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(new AccessTokenInterceptor( new SimpleAccessTokenProvider(accessToken) ));
    Retrofit.Builder retrofit = new Retrofit.Builder();

    return new BingCommerceIngestionImpl(httpClient, retrofit);
}
~~~

### Manage your Index

You can create and manage you index using the Bing for Commerce portal. However, you could also use the SDK to manage your indexes.

#### Create an index
~~~java
private ResponseIndex createIndex(final BingCommerceIngestion ingestionClient, final String tenantId, final String indexName) {

    // Prepare the index fields
    IndexField idField = new IndexField()
            .withName("ProductId")
            .withType(IndexFieldType.PRODUCT_ID) // Exactly one Product Id field is required while creating an index.
            .withFilterable(true)
            .withRetrievable(true); 
    IndexField titleField = new IndexField()
            .withName("ProductTitle")
            .withType(IndexFieldType.TITLE)
            .withRetrievable(true)
            .withSearchable(true);
    IndexField descriptionField = new IndexField()
            .withName("ProductDescription")
            .withType(IndexFieldType.DESCRIPTION)
            .withRetrievable(true)
            .withSearchable(true);

    // Prepare the Index using the prepared fields
    Index newIndexReq = new Index()
            .withName(indexName)
            .withDescription("My sample index description")
            .withFields(Arrays.asList(idField, titleField, descriptionField));

    // Create the index
    IndexResponse createResponse = ingestionClient.createIndex(tenantId, null,newIndexReq);
    return createResponse.indexes().get(0);
}
~~~

#### Get all indexes
~~~java
IndexResponse allIndexesResponse = ingestionClient.getAllIndexes(tenantId);
~~~

#### Get an index by Id
~~~java
IndexResponse myIndexResponse = ingestionClient.getIndex(tenantId, indexId);
~~~

### Pushing data

The APIs to push data to Bing for Commerce are asynchronous, where the service / SDK contains two separate APIs to serve this, one for the push itself, and another to track down the status.

The content that you will be pushing to your index catalog needs to match the index configuration that you have the index created with, and it can be in any of the following formats:
* JSon Array.
* ND-JSon (New-Line Delimited JSon).
* CSV.
* TSV.

Please note however that if you have a transformation config added to your index, the format of the pushed data needs to match that of what your transformation script is expecting.

#### Push Data
~~~java
private String pushData(final BingCommerceIngestion ingestionClient, final String tenantId, final String indexId, final String content) {

    PushDataUpdateResponse pushResponse = ingestionClient.pushDataUpdate(content, tenantId, indexId);

    return pushResponse.updateId();
}
~~~

#### Push Data Status
~~~java
private String pushDataStatus(final BingCommerceIngestion ingestionClient, final String tenantId, final String indexId, final String pushDataUpdateId) {

    PushUpdateStatusResponse status = client.pushDataStatus(TENANT_ID, indexId, pushDataUpdateId);

    // returns the overall status for the push call.
    //
    // You can get the status for each record being updated by accessing status.records() list.
    return status.status();
}
~~~

### Search Query

You can use the Search SDK to do queries on your Bing for Commerce indexes given that you have an access token with the proper scope. 

#### Simple Search Query
There are few cusomizations that you can still apply to the simple search query api by providing different values for different API arguments (like: market, language, field select, order configuration, pagination, facet discovery and query alteration toggle).
~~~java
private ResponseItemsBase simpleSearch(final BingCommerceSearch searchClient, final String tenantId, final String indexId, final String queryTerm) {

    CommerceSearchResponse response = searchClient.searchs().get(queryTerm, tenandId, indexId);

    return response.items();
}
~~~

#### Advanced Search Query
You can do a lot more customization (like filering, boosting, ...etc) to your advanced search query by providing a detailed search query description for how you want your results to be.
~~~java
private ResponseItemsBase advancedSearch(final BingCommerceSearch searchClient, final String tenantId, final String indexId) {

    // Prepare the Search request.
    CommerceSearchPostRequest request = new CommerceSearchPostRequest()
            .withQuery(new RequestQuery().withFilter(
                new StringSetCondition()
                    .withValues(Arrays.asList("First Condition", "Second Condition"))
                    .withField("My Search Field"))
            .withItems(new RequestItems().withSelect( Arrays.asList("*") ))
            .withAggregations(Arrays.asList(new RequestDiscoverFacets().withName("discovered facets")));

    // Send the search request.
    CommerceSearchResponse response = searchClient.searchs().post(request, tenantId, indexId);

    return response.items();
}
~~~

### Transformation Script Management
You can upload a custom configuration that you might need applied to the data you push to your index automatically. Please refer to the [Bing for Commerce docs](https://commerce.bing.com/docs/product-search/#transformation-script-management-api) for more details about how to create a valid transformation config.

#### Create or Update the transformation config
~~~java
String myScript = GetMyTransformationScript();
TransformationConfigResponse createScriptResponse = ingestionClient.createOrUpdateTransformationConfig(myScript, tenantId, indexId);
~~~

#### Get the existing tranformation config
~~~java
// Note that the getTransformationConfig will throw a 400 Bad Request if your index doesn't have a transformation config.
TransformationConfigResponse readScriptResponse = client.getTransformationConfig(tenantId, indexId);
String myScript = readScriptResponse.value();
~~~

#### Delete the transformation config
~~~java
TransformationConfigResponse deleteScriptResponse = client.deleteTransformationConfig(tenantId, indexId);
~~~

### Transformation Script Tryout
Before you associate a transformation script to your index, you can use the transformation tryout apis to make sure that your index works with your data and the SDK before actually associating it to your index.

#### Upload a tranformation config tryout
~~~java
private String UploadTransformationTryout(String script) {

    TransformationConfigResponse createScriptResponse = client.uploadTryOutConfig(script);

    return createScriptResponse.tryOutId();
}
~~~

#### Test the transformation config tryout
~~~java
private bool ExecuteTransformationTryout(String data, String tryOutId) {

    TransformationTryoutResponse executeResponse = client.executeTryOutConfig(data, tryOutId);

    return executeResponse.status() == "Succeeded";
}
~~~

## Samples

Please take a look at the [sample](./samples/) for a quick example for how to use the SDK in order to manage your indexes, push data to your index catalog and perform search queries on your data.


## Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.microsoft.com.

When you submit a pull request, a CLA-bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., label, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.
