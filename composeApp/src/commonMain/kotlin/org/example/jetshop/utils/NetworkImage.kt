package org.example.jetshop.utils


//@Composable
//fun NetworkImage(
//    url: String,
//    modifier: Modifier = Modifier,
//    contentDescription: String? = null,
//    contentScale: ContentScale = ContentScale.Crop,
//    imageSize: Dp = 100.dp
//) {
//    var isLoading by remember { mutableStateOf(true) }
//    var hasError by remember { mutableStateOf(false) }
//
//    Box(
//        modifier = modifier
//            .size(imageSize)
//            /*.clip(RoundedCornerShape(8.dp))
//            .background(MaterialTheme.colorScheme.surface)*/,
//                contentAlignment = Alignment.Center
//    ) {
//        val painter = rememberAsyncImagePainter(
//            model = url,
//            imageLoader = ImageLoader(LocalContext.current),
//            onState = { state ->
//                isLoading = state is AsyncImagePainter.State.Loading
//                hasError = state is AsyncImagePainter.State.Error
//            }
//        )
//
//        if (isLoading) {
//            CenteredCircularProgressIndicator()
//        }
//
//        if (hasError) {
//            Icon(
//                imageVector = Icons.Default.BrokenImage,
//                contentDescription = "Image Load Failed",
//                modifier = Modifier.align(Alignment.Center),
//                tint = Color.Gray
//            )
//        }
//
//        Image(
//            painter = painter,
//            contentDescription = contentDescription,
//            contentScale = contentScale,
//            modifier = Modifier.matchParentSize().alpha(if (isLoading || hasError) 0f else 1f)
//        )
//    }
//
//}
