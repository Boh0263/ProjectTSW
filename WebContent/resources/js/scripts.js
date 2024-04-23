import * as THREE from 'three';
import { loadTextures } from "./textureloader.js";
import { OrbitControls } from 'orbitcontrols';
import { GLTFLoader } from 'gltfloader';

const renderer = new THREE.WebGLRenderer();

const newUrl = new URL("../static/scene1.gltf", import.meta.url);
const pedestalUrl = new URL("../static/pedestal.gltf", import.meta.url);

renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );

camera.position.set(-10,30,30);
camera.lookAt(0,0,0);

const orbit = new OrbitControls( camera, renderer.domElement );
const loader = new GLTFLoader();
const planeGeometry = new THREE.PlaneGeometry(60, 60, 256 );
let texturearray = loadTextures();

const plane = new THREE.Mesh( planeGeometry, texturearray[0]);
plane.rotation.x = Math.PI / 2;

function onWindowResize() {

  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();

  renderer.setSize( window.innerWidth, window.innerHeight );
}
window.addEventListener( 'resize', onWindowResize, false );

scene.add(plane);

loader.load(newUrl.toString(), function ( gltf ) {
  const model = gltf.scene;
  const clock = new THREE.Clock(true);
  model.animations = gltf.animations;
  model.position.set(0,0.05,0);  
  const mixer = new THREE.AnimationMixer(model);
  scene.add(model);
  const clips = model.animations;
  clips.forEach((clip) => {
    const action = mixer.clipAction(clip);
    action.play();
    animate();
  });
  
loader.load(pedestalUrl.toString(), function ( gltf ){
  const pmodel = gltf.scene;
  pmodel.position.set(0,0,-10);
	gltf.scene.traverse(function (o){
		if (o.isMesh){
			o.material = texturearray[1];
		}
		
	});
	 
	 
	 
 }); 

  function animate() {
   if (mixer) {
    mixer.update(clock.getDelta());  
    model.rotation.y += 0.002;
  }
    renderer.render(scene, camera);
    requestAnimationFrame(animate);
  }

});

/*const axesHelper = new THREE.AxesHelper( 5 );
scene.add( axesHelper );*/
const ambientLight = new THREE.AmbientLight( 0x404040, 40 ); // soft white light
scene.add( ambientLight );
orbit.update();
renderer.render(scene, camera );



function anima(time){
    requestAnimationFrame( anima );
    orbit.update();
    renderer.render( scene, camera );
}

renderer.setAnimationLoop(anima(2));