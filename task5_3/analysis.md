# Colour Contrast Analysis

## Source colours

The supplied `text.css` uses `color: #327832` and
`background-color: #dff0d8`. The original page and stylesheet were recovered
from the [course starter repository](https://github.com/COMP2850-2627/ktor-work/tree/a79ba615487040966f3f1c4f1e1db6fddb340480/tasks/task5_3).

This analysis follows the arithmetic in the
[Task 5.3 instructions](https://comp2850.github.io/ktor-guide/a11y/wcag#task-5-3),
without using an accessibility scanner or contrast-checking tool.

## Relative luminances

Hexadecimal to decimal conversion:

- Text: `#327832` → `(3×16+2, 7×16+8, 3×16+2)` → RGB `(50, 120, 50)`.
- Background: `#dff0d8` → `(13×16+15, 15×16+0, 13×16+8)` → RGB `(223, 240, 216)`.

Normalize each channel using `c_sRGB = c_8bit / 255`. Then linearize it:

- If `c_sRGB ≤ 0.04045`, use `c_linear = c_sRGB / 12.92`.
- Otherwise, use `c_linear = ((c_sRGB + 0.055) / 1.055)^2.4`.

All six channels here exceed 0.04045, so the second formula applies.
For example, the text's red channel is
`((50/255 + 0.055) / 1.055)^2.4 = 0.0318960331`.

| Colour | Normalized R | Normalized G | Normalized B | Linear R | Linear G | Linear B |
| --- | --- | --- | --- | --- | --- | --- |
| Text | 0.1960784314 | 0.4705882353 | 0.1960784314 | 0.0318960331 | 0.1878207723 | 0.0318960331 |
| Background | 0.8745098039 | 0.9411764706 | 0.8470588235 | 0.7379104088 | 0.8713671192 | 0.6866853124 |

Relative luminance is `L = 0.2126R_linear + 0.7152G_linear + 0.0722B_linear`.

Text:

`L_text = 0.2126×0.0318960331 + 0.7152×0.1878207723 + 0.0722×0.0318960331`

`L_text ≈ 0.1434134066`.

Background:

`L_background = 0.2126×0.7379104088 + 0.7152×0.8713671192 + 0.0722×0.6866853124`

`L_background ≈ 0.8296601961`.

## Contrast ratio

The background is lighter, so it is the numerator:

`C = (L_background + 0.05) / (L_text + 0.05)`

`C = (0.8296601961 + 0.05) / (0.1434134066 + 0.05)`

`C ≈ 4.5480828435 : 1`.

The calculations retain precision before rounding the displayed values.

## Conclusion

The page includes an ordinary paragraph, so the normal-size text thresholds
must be satisfied, regardless of whether the heading counts as large text.

| Criterion | Normal-size threshold | Result for the paragraph |
| --- | --- | --- |
| WCAG 1.4.3, minimum contrast (AA) | 4.5:1 | Pass: 4.54808 ≥ 4.5 |
| WCAG 1.4.6, enhanced contrast (AAA) | 7:1 | Fail: 4.54808 < 7 |

For large-scale text the thresholds are 3:1 and 4.5:1 respectively, so a
qualifying large heading would pass both. However, the paragraph prevents
the page as a whole from satisfying 1.4.6. The answer is **1.4.3 only**.
This conclusion concerns these contrast criteria; it does not establish
complete WCAG AA conformance.
