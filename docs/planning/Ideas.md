- 2d esolangs usually don't have nice support for strings and lists
- big brain idea: esolang with nice support for strings and lists

- commands can be enclosed in `{}` to make it a single transversable unit. 
- `'n` encloses the next n horizontal characters
- vertical enclosing to: `^¶¶¶v`
- directions = `VA<>`
- `[name¶...¶]` defines a block macro. `@name;` to call

```
/...
....
.../
```

 --> Inline block. Box bounded by slashes.

```
/.......
../.....
....../.
......./
``` 
--> nestable

- `~` to turn a thing left into a function
- `!` to turn a thing up into a function
- Those can be anywhere below a block or right of a block and still get the whole block. 

- Modifiers get from:

```
    4
    ^
1 < o > 3
    v
    2
```

However, can be overriden. 

`qwer` -> `<v>^` for modifiers. Always read from right of modifier:

```
oeq
```

--> gets from right then left. 

```
oE2e
```

--> gets from twice over right then right

- Strings: `(contents)`. `\)` for literal close paren. Nestable like jelly. Reads until last thing in a sequential manner. Newlines are removed. Parses with priority over everything else. 

- Numeric literals are full literals

- `#` halts

- `§` is comment

- single `.` is NOP

**Examples**

```
(Hello, World!)# § Prints HW
```

```
100 /3 5 p % 0 ~ M (¶) j #
. (FizzBuzz) 2 * +~ R 
. G . . . /
```

Alternatively

```
100 @a; ~ M (¶) j

[a
3 5 p % 0 V
. V * 2 (FizzBuzz) <
. G
. |
]