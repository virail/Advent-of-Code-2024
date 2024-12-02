console.log("Hello via Bun!");

const inFile = Bun.file("location.txt");

const text = await inFile.text();
const locations = text.split("\n");
var firstList: any = [];
var secondList: any = [];

locations.map((location) => {
    const split = location.split("   ")
    firstList.push(split[0]);
    secondList.push(split[1]);
    // console.log("first split:", split[0]);
    // console.log("second split:", split[1]);
})

console.log("firstList size:", firstList.length);

firstList.sort();
secondList.sort();
console.log("firstList:", firstList.slice(0, 10));

var diff = 0;
for (var i = 0; i < firstList.length; i++) {
    diff += Math.abs(Number(firstList[i]) - Number(secondList[i]));
}

console.log("diff:", diff);

// Solution for day 1 puzzle 1;

var similarityScore = 0;

for (var i = 0; i < firstList.length; i++) {
    for (var j = 0; j < secondList.length; j++) {
        if (Number(firstList[i]) === Number(secondList[j])) {
            similarityScore += Number(firstList[i]);
            // console.log("MATCH: ", firstList[i], secondList[j]);
        }
    }
}

console.log("similarityScore:", similarityScore);