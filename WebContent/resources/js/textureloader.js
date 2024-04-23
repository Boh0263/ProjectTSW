/**
 * 
 */
import * as THREE from 'three';
export default function loadTextures() {
	  
	  const floorUrlstd = new URL("../static/textures/Material_2077.jpg", import.meta.url);
	  const floorUrlnormal = new URL("../static/textures/Stylized_Stone_Floor_005_normal.jpg", import.meta.url);
	  const floorUrlroughness = new URL("../static/textures/Stylized_Stone_Floor_005_roughness.jpg", import.meta.url);
	  const floorUrlheight = new URL("../static/textures/Stylized_Stone_Floor_005_height.png", import.meta.url);
	  const floorUrlbc = new URL("../static/textures/Stylized_Stone_Floor_005_basecolor.jpg", import.meta.url);
	  const floorUrlamoc = new URL("../static/textures/Stylized_Stone_Floor_005_ambientOcclusion.jpg", import.meta.url);

	  const woodUrl = new URL("../static/textures/Material_1966.jpg", import.meta.url);
	  const woodUrlnormal = new URL("../static/textures/Wood_022_normal.jpg", import.meta.url);
	  const woodUrlbc = new URL("../static/textures/Wood_022_basecolor.jpg", import.meta.url);
	  const woodUrlamoc = new URL("../static/textures/Wood_022_ambientOcclusion.jpg", import.meta.url);
	  const woodUrlrough = new URL("../static/textures/Wood_022_roughness.jpg", import.meta.url);
	  const woodUrlheight = new URL("../static/textures/Wood_022_height.png", import.meta.url);
	  
	  const textureloader = new THREE.TextureLoader();
	  
	  const material = textureloader.load(floorUrlstd);
	  const mat_normal = textureloader.load(floorUrlnormal);
	  const mat_rough = textureloader.load(floorUrlroughness);
	  const mat_height = textureloader.load(floorUrlheight);
	  const mat_bc = textureloader.load(floorUrlbc);
	  const mat_amoc = textureloader.load(floorUrlamoc);
	  
	  const wood = textureloader.load(woodUrl);
	  const wood_normal = textureloader.load(woodUrlnormal);
	  const wood_rough = textureloader.load(woodUrlrough);
	  const wood_height = textureloader.load(woodUrlheight);
	  const wood_bc = textureloader.load(woodUrlbc);
	  const wood_amoc = textureloader.load(woodUrlamoc);
	  wood_normal.flipY = false;
	  
	  let floor_material = new THREE.MeshStandardMaterial(
			  {
			    map: mat_bc,
			    normalMap: mat_normal,
			    displacementMap: mat_height,
			    displacementScale: 0.4,
			    roughnessMap: mat_rough,
			    roughness: 0.7,
			    aoMap: mat_amoc,
			    side: THREE.DoubleSide
			    
			  });
	  let wood_material = new THREE.MeshStandardMaterial(
			  {
				 map: wood_bc,
			     normalMap: wood_normal,
			     displacementMap: wood_height,
			     displacementScale: 0.0,
			     roughnessMap: wood_rough,
			     roughness: 0.01,
			     metalness: 0.1,
			     aoMap: wood_amoc,
			     side: THREE.DoubleSide
		 
	          });
	  
     return [floor_material, wood_material];	  	 
 }