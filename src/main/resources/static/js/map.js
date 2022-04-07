
function initMap() {
    const directionsService = new google.maps.DirectionsService();
    const directionsRenderer = new google.maps.DirectionsRenderer();
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 7,
        center: { lat: -37.907803, lng: 145.133957 },
    });

    directionsRenderer.setMap(map);

    const onChangeHandler = function () {
        calculateAndDisplayRoute(directionsService, directionsRenderer);
        calculateThePrompt();
    };
    const onChangeHandler2 = function () {
        calculateThePrompt();
    };

    //document.getElementById("start").addEventListener("change", onChangeHandler);
   // document.getElementById("end").addEventListener("change", onChangeHandler);
     document.getElementById("div_calculte").addEventListener("click", onChangeHandler);
   // document.getElementById("div_calculte").addEventListener("click", calculateThePrompt);
}

function calculateAndDisplayRoute(directionsService, directionsRenderer) {
    directionsService
        .route({
            origin: {
                query: document.getElementById("start").value,
            },
            destination: {
                query: document.getElementById("end").value,
            },
            travelMode: google.maps.TravelMode.DRIVING,
        })
        .then((response) => {
            directionsRenderer.setDirections(response);
        })
        .catch((e) => window.alert("Directions request failed due to " + status));
}

// function calculateThePrompt(){
//     var origins = document.getElementById("start").value;
//     var destinations = document.getElementById("end").value;
//     var axios = require('axios');
//
//     var config = {
//         method: 'get',
//         url: 'https://maps.googleapis.com/maps/api/distancematrix/json?origins=849VCWC8%2BR9&destinations=San%20Francisco&key=AIzaSyB-6bcvh7VwQmlPe6FAWRz7seZAwbrLHdA',
//         headers: { }
//     };
//
//     axios(config)
//         .then(function (response) {
//             console.log(JSON.stringify(response.data));
//
//         })
//         .catch(function (error) {
//             console.log(error);
//         });
//     // $.ajax({
//     //     type: "post",
//     //     url: "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Monash City&destinations=Monash Clayton&key=AIzaSyB-6bcvh7VwQmlPe6FAWRz7seZAwbrLHdA",
//     //     // data: {origins:origins,
//     //     //     destinations:destinations,
//     //     //     units:"imperial",
//     //     //     key:"AIzaSyB-6bcvh7VwQmlPe6FAWRz7seZAwbrLHdA"},
//     //     success:function() {
//     //         // var jsonRes = result;
//     //         // var obj = JSON.parse(json);
//     //         // $('#calculate_time').html(obj.rows.elements.duration.text);
//     //         alert("success");
//     //     },
//     //     error:function(result) {
//     //         alert('error123');
//     //
//     //     }
//     // });
// }
function calculateThePrompt() {
    var origins = document.getElementById("start").value;
    var destinations = document.getElementById("end").value;
    var service = new google.maps.DistanceMatrixService();
    const request = {
        origins: [origins],
        destinations: [destinations],
        travelMode: google.maps.TravelMode.DRIVING,
        unitSystem: google.maps.UnitSystem.METRIC,
        avoidHighways: false,
        avoidTolls: false,
    };
    service.getDistanceMatrix(request).then((response) => {
        // put response
        console.log(response);
        const type = document.getElementById("type").value;
        const distance = response.rows[0].elements[0].distance.value;
        const duration = response.rows[0].elements[0].duration.value;
        $.ajax({
            type: "post",
            url: "/CalculatorController/useMapCalculator",
            data: {type: type,
                    distance:distance,
                    duration:duration},
            success:function(result) {


                $('#prompt_result').html(result);
                document.getElementById("prompt_result").innerHTML =result;

            },
            error:function(result) {
                alert('error');

            }
        });
    });
}